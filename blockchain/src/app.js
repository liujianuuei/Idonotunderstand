'use strict';

var express = require("express");
var bodyParser = require('body-parser');

var chain = require('./chain');
var peer = require('./peer');

var portApp = process.env.HTTP_PORT || 3001;
var portP2P = process.env.P2P_PORT || 6001;
var theTargetPeers = process.env.PEERS ? process.env.PEERS.split(',') : [];

var startAppServer = () => {
    var app = express();
    app.use(bodyParser.json());

    app.get('/blocks', (req, res) => res.send(JSON.stringify(chain.blockchain)));
    app.post('/block', (req, res) => {
        peer.addNewBlock(req.body.data);
        res.send('Success: block added');
    });

    app.get('/peers', (req, res) => {
        res.send(peer.peers.map(p => p + ':' + p._socket.remoteAddress + ':' + p._socket.remotePort));
    });
    app.post('/peer', (req, res) => {
        peer.addNewPeer([req.body.peer]);
        res.send('Success: peer added');
    });

    app.listen(portApp, () => console.log('Listening http on port: ' + portApp));
};

startAppServer();
peer.startPeerServer(portP2P);
peer.addNewPeer(theTargetPeers);