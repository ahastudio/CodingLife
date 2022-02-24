// SPDX-License-Identifier: UNLICENSED

pragma solidity ^0.8.0;

import "./MultiSigWallet.sol";

contract MultiSigWalletFactory {
    // Storage

    address[] public wallets;

    // Events

    event WalletCreation(address sender, address wallet);

    // Methods

    function createWallet(
        string memory _name, address[] memory _owners, uint8 _required
    ) public returns (address wallet) {
        wallet = address(new MultiSigWallet(_name, _owners, _required));

        wallets.push(wallet);

        emit WalletCreation(msg.sender, wallet);
    }

    // Getters

    function getWalletsCount() public view returns (uint count) {
        count = wallets.length;
    }

    function getWallet(uint _index) public view returns (address wallet) {
        require(_index < wallets.length, "Out of range");
        wallet = wallets[_index];
    }
}
