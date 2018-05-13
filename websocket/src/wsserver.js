'use strict';

var WebSocket = require('ws');

let wss = new WebSocket.Server({port: 8181});

wss.broadcast = (message) => {
    wss.clients.forEach((client) => {
        if (client.readyState === WebSocket.OPEN) {
            client.send(message);
        }
    })
};

wss.on('connection', (websocket, req) => {
    console.log('client connected', req.connection.remoteAddress, websocket.protocol, websocket.url, websocket._socket.remoteAddress, websocket._socket.remotePort);
    websocket.on('message', (message) => {
        console.log('wss: received', message);
    });
    websocket.on('close', (event) => {
        console.log('wss: connection closed', event);
    });
    websocket.on('error', (event) => {
        console.log('wss: connection failed', event);
    });
    wss.broadcast('message from server');
});