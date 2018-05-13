'use strict';

var WebSocket = require("ws");
var chain = require('./chain');

var thePeers = [];
exports.peers = thePeers;

var MessageType = {
    BLOCK_LATEST: 0,
    BLOCK_ALL: 1
};

var startPeerServer = (portP2P) => {
    var wss = new WebSocket.Server({port: portP2P});
    wss.on('connection', websocket => {
        thePeers.push(websocket);
        websocket.on('message', (data) => {
            resolve(JSON.parse(data), websocket);
        });
        onError(websocket);
        websocket.send(JSON.stringify({'type': MessageType.BLOCK_LATEST}));
    });
    console.log('Listening websocket p2p port on: ' + portP2P);
};
exports.startPeerServer = startPeerServer;

var connectToPeerServers = (peers) => {
    peers.forEach((address) => {
        var websocket = new WebSocket(address);
        websocket.on('open', () => {
            thePeers.push(websocket);
            websocket.on('message', (data) => {
                resolve(JSON.parse(data), websocket);
            });
            onError(websocket);
            websocket.send(JSON.stringify({'type': MessageType.BLOCK_LATEST}));
        });
        websocket.on('error', () => {
            console.log('connection failed')
        });
    });
};
exports.addNewPeer = connectToPeerServers;

var removeFailedPeer = (p) => {
    thePeers.splice(thePeers.indexOf(p), 1);
};

var resolve = (message, websocket) => {
    if (message.data) { // TODO check logic below
        var receivedBlocks = JSON.parse(message.data).sort((b1, b2) => (b1.index - b2.index));
        var latestBlockReceived = receivedBlocks[receivedBlocks.length - 1];
        var latestBlockHeld = chain.getLatestBlock();
        if (latestBlockReceived.index > latestBlockHeld.index) {
            console.log('blockchain possibly behind. We got: ' + latestBlockHeld.index + ' Peer got: ' + latestBlockReceived.index);
            if (latestBlockHeld.hash === latestBlockReceived.previousHash) {
                console.log("We can append the received block to our chain");
                chain.blockchain.push(latestBlockReceived);
                broadcast({
                    'type': MessageType.BLOCK_LATEST,
                    'data': JSON.stringify([chain.getLatestBlock()])
                });
            } else if (receivedBlocks.length === 1) {
                console.log("We have to query the chain from our peer");
                broadcast({'type': MessageType.BLOCK_ALL});
            } else {
                console.log("Received blockchain is longer than current blockchain");
                chain.replaceChain(receivedBlocks);
            }
        } else {
            console.log('received blockchain is not longer than current blockchain. Do nothing');
        }
    } else {
        if (message.type === MessageType.BLOCK_LATEST) {
            websocket.send(JSON.stringify({
                'type': MessageType.BLOCK_LATEST,
                'data': JSON.stringify([chain.getLatestBlock()])
            }));
        } else if (message.type === MessageType.BLOCK_ALL) {
            websocket.send(JSON.stringify({
                'type': MessageType.BLOCK_ALL, 'data': JSON.stringify(chain.blockchain)
            }));
        }
    }
};

var addNewBlock = (data) => {
    var newBlock = chain.generateNextBlock(data);
    chain.addBlock(newBlock);
    broadcast({
        'type': MessageType.BLOCK_LATEST,
        'data': JSON.stringify([chain.getLatestBlock()])
    });
};
exports.addNewBlock = addNewBlock;

var broadcast = (message) => thePeers.forEach(socket => socket.send(JSON.stringify(message)));

var onError = (websocket) => {
    var cleanup = (websocket) => {
        console.log('peer connection failed');
        removeFailedPeer(websocket);
    };
    websocket.on('close', () => cleanup(websocket));
    websocket.on('error', () => cleanup(websocket));
};