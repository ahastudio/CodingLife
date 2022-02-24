// SPDX-License-Identifier: UNLICENSED

pragma solidity ^0.8.0;

contract MultiSigWallet {
    // Constants

    uint public constant MAX_OWNERS_COUNT = 10;

    // Types

    struct Transaction {
        address sender;
        address to;
        uint value;
        bytes data;
    }

    // Storage

    string private _name;

    mapping(address => bool) private _isOwner;
    address[] private _owners;

    uint8 private _required;

    uint private _nonce = 1;

    mapping(uint => Transaction) private _transactions;
    uint[] private _pendings;
    mapping(uint => mapping(address => bool)) private _confirmations;

    // Events

    event Deposit(address indexed sender, uint value);
    event Received(address indexed sender, uint value);
    event Request(address indexed sender, uint indexed transactionId);
    event Confirmation(address indexed sender, uint indexed transationId);
    event Revocation(address indexed sender, uint indexed transactionId);
    event Execution(uint indexed transactionId);

    // Modifiers

    modifier onlyOwner() {
        require(_isOwner[msg.sender], "Permission: only owner");
        _;
    }

    modifier transactionExists(uint transactionId) {
        require(_transactions[transactionId].to != address(0),
            "Transaction not found");
        _;
    }

    // Constructor

    constructor(
        string memory name, address[] memory owners, uint8 required
    ) {
        require(owners.length < MAX_OWNERS_COUNT, "Check owners count");

        for (uint i = 0; i < owners.length; i++) {
            require(owners[i] != address(0), "Invliad owner address");
            _isOwner[owners[i]] = true;
        }

        _name = name;
        _owners = owners;
        _required = required;
    }

    // Payable

    fallback() external payable {
        // require(msg.value > 0);
        emit Deposit(msg.sender, msg.value);
    }

    receive() external payable {
        emit Received(msg.sender, msg.value);
    }

    // Methods

    function requestTransaction(
        address to, uint256 value, bytes calldata data
    ) public onlyOwner returns (uint transactionId) {
        transactionId = addTransaction(to, value, data);

        emit Request(msg.sender, transactionId);

        confirmTransaction(transactionId);
    }

    function confirmTransaction(
        uint transactionId
    ) public onlyOwner transactionExists(transactionId) {
        _confirmations[transactionId][msg.sender] = true;

        emit Confirmation(msg.sender, transactionId);

        executeTransaction(transactionId);
    }

    function revokeTransaction(
        uint transactionId
    ) public onlyOwner transactionExists(transactionId) {
        _confirmations[transactionId][msg.sender] = false;

        if (_transactions[transactionId].sender == msg.sender) {
            delete _transactions[transactionId];
            removePendingTransaction(transactionId);
        }

        emit Revocation(msg.sender, transactionId);
    }

    // Getters

    function getName() external view returns (string memory name) {
        name = _name;
    }

    function getOwnersCount() external view returns (uint count) {
        count = _owners.length;
    }

    function getOwner(uint8 index) external view returns (address owner) {
        require(index < _owners.length, "Out of range");
        owner = _owners[index];
    }

    function getRequired() external view returns (uint required) {
        required = _required;
    }

    function getTransaction(uint transactionId) external view
    returns (Transaction memory transaction) {
        transaction = _transactions[transactionId];
    }

    function getPendingsCount() external view returns (uint count) {
        count = _pendings.length;
    }

    function getPendingTransactionId(
        uint index
    ) external view returns (uint transactionId) {
        transactionId = _pendings[index];
    }

    function getConfirmationsCount(
        uint transactionId
    ) external view transactionExists(transactionId) returns (uint count) {
        for (uint i = 0; i < _owners.length; i++) {
            if (_confirmations[transactionId][_owners[i]]) {
                count += 1;
            }
        }
    }

    function isConfirmed(
        uint transactionId
    ) external view transactionExists(transactionId) returns (bool) {
        return this.getConfirmationsCount(transactionId) >= _required;
    }

    function getConfirmed(
        uint transactionId, address owner
    ) external view transactionExists(transactionId) returns (bool) {
        return _confirmations[transactionId][owner];
    }

    // Internals

    function addTransaction(
        address to, uint value, bytes calldata data
    ) internal returns (uint transactionId) {
        require(to != address(0), "Invalid `to` address");
        require(value > 0, "Amount must be positive number");

        transactionId = _nonce;

        _transactions[transactionId] = Transaction(
            { sender: msg.sender, to: to, value: value, data: data }
        );

        _pendings.push(transactionId);

        _nonce += 1;
    }

    function executeTransaction(uint transactionId) internal {
        if (!this.isConfirmed(transactionId)) {
            return;
        }

        Transaction storage transaction = _transactions[transactionId];

        address to = transaction.to;
        _transactions[transactionId].to = address(0);

        removePendingTransaction(transactionId);

        execute(to, transaction.value, transaction.data);

        emit Execution(transactionId);

        delete _transactions[transactionId];
    }

    function removePendingTransaction(uint transactionId) internal {
        for (uint i = 0; i < _pendings.length; i++) {
            if (transactionId == _pendings[i]) {
                _pendings[i] = _pendings[_pendings.length - 1];
                break;
            }
        }
        _pendings.pop();
    }

    function execute(address to, uint value, bytes memory data) internal {
        // solhint-disable-next-line avoid-low-level-calls
        (bool success,) = to.call{value: value}(data);
        require(success, "Call faield");
    }
}
