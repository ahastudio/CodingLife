pragma solidity ^0.4.23;

import "./MultiSigWallet.sol";

contract MultiSigWalletFactory {
    // Storage

    address[] public wallets;

    // Events

    event WalletCreation(address sender, address wallet);

    // Methods

    function createWallet(string _name, address[] _owners, uint8 _required)
    public
    returns (address wallet) {
        wallet = new MultiSigWallet(_name, _owners, _required);

        wallets.push(wallet);

        emit WalletCreation(msg.sender, wallet);
    }

    // Getters

    function getWalletsCount() public constant returns (uint count) {
        count = wallets.length;
    }

    function getWallet(uint _index) public constant returns (address wallet) {
        require(_index < wallets.length);
        wallet = wallets[_index];
    }
}
