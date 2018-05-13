'use strict';

var algorithm = require('./helper/algorithm');
var block = require('./block');

var blockchain = [block.getGenesisBlock()];

exports.blockchain = blockchain;

exports.isValidChain = (blockchainToValidate, genesisBlock) => {
    if (JSON.stringify(blockchainToValidate[0]) !== JSON.stringify(genesisBlock)) {
        return false;
    }
    var tempBlocks = [blockchainToValidate[0]];
    for (var i = 1; i < blockchainToValidate.length; i++) {
        if (block.isValidBlock(blockchainToValidate[i], tempBlocks[i - 1])) {
            tempBlocks.push(blockchainToValidate[i]);
        } else {
            return false;
        }
    }
    return true;
};

exports.replaceChain = (newBlocks) => {
    if (isValidChain(newBlocks) && newBlocks.length > blockchain.length) {
        console.log('Received blockchain is valid. Replacing current blockchain with received blockchain');
        blockchain = newBlocks;
        broadcast(responseLatestMsg());
    } else {
        console.log('Received blockchain invalid');
    }
};

exports.getLatestBlock = () => blockchain[blockchain.length - 1];

exports.generateNextBlock = (blockData) => {
    var previousBlock = getLatestBlock();
    var nextIndex = previousBlock.index + 1;
    var nextTimestamp = new Date().getTime() / 1000;
    var nextHash = algorithm.calculateHash(nextIndex, previousBlock.hash, nextTimestamp, blockData);
    return new block.Block(nextIndex, previousBlock.hash, nextTimestamp, blockData, nextHash);
};

exports.addBlock = (newBlock) => {
    if (block.isValidBlock(newBlock, getLatestBlock())) {
        blockchain.push(newBlock);
    }
};