// SPDX-License-Identifier: UNLICENSED

pragma solidity ^0.8.0;

contract Migrations {
    address public owner;
    uint public _lastCompletedMigration;

    constructor() {
        owner = msg.sender;
    }

    modifier restricted() {
        if (msg.sender == owner) _;
    }

    function setCompleted(uint completed) public restricted {
        _lastCompletedMigration = completed;
    }

    function upgrade(address newAddress) public restricted {
        Migrations upgraded = Migrations(newAddress);
        upgraded.setCompleted(_lastCompletedMigration);
    }
}
