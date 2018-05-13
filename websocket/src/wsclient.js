'use strict';

var WebSocket = require('ws');

var websocket = new WebSocket('ws://localhost:8181');

websocket.on('open', () => {
    console.log('server connected', websocket.protocol, websocket.url, websocket._socket.remoteAddress, websocket._socket.remotePort);
    websocket.send('message from client');
});
websocket.on('message', (message) => {
    console.log('received', message);
});
websocket.on('close', (event) => {
    console.log('connection closed', event);
});
websocket.on('error', (event) => {
    console.log('connection failed', event);
});