'use strict';

var algorithm = require('./helper/algorithm');

exports.Block = class {
    constructor(index, previousHash, timestamp, data, hash) {
        this.index = index;
        this.previousHash = previousHash.toString();
        this.timestamp = timestamp;
        this.data = data;
        this.hash = hash.toString();
    }
};

exports.getGenesisBlock = () => {
    return new Block(0, "0", 1465154705, "my genesis block!!", "816534932c2b7154836da6afc367695e6337db8a921823784c14378abed4f7d7");
};

exports.isValidBlock = (block, previousBlock) => {
    if (previousBlock.index + 1 !== block.index) {
        console.log('invalid index');
        return false;
    } else if (previousBlock.hash !== block.previousHash) {
        console.log('invalid previoushash');
        return false;
    } else if (algorithm.calculateHashForBlock(block) !== block.hash) {
        console.log(typeof (block.hash) + ' ' + typeof algorithm.calculateHashForBlock(block));
        console.log('invalid hash: ' + algorithm.calculateHashForBlock(block) + ' ' + block.hash);
        return false;
    }
    return true;
};