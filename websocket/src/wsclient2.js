'use strict';

var WebSocket = require('ws');

var websocket = new WebSocket('ws://localhost:8181');

websocket.onopen = (event) => {
    console.log('connected to server', websocket.protocol); // check the protocol chosen by the server
    websocket.send('message from client');
};
websocket.onmessage = (event) => {
    console.log('received', event.data);
};
websocket.onclose = (event) => {
    console.log('connection closed');
};
websocket.onerror = (event) => {
    console.log('connection failed');
};