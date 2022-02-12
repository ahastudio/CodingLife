pragma solidity ^0.4.23;

contract MultiSigWallet {
    // Constants

    uint constant public MAX_OWNERS_COUNT = 10;

    // Types

    struct Transaction {
        address sender;
        address to;
        uint value;
        bytes data;
    }

    // Storage

    string public name;

    mapping(address => bool) public isOwner;
    address[] public owners;

    uint8 public required;

    uint public nonce = 1;

    mapping(uint => Transaction) public transactions;
    uint[] public pendings;
    mapping(uint => mapping(address => bool)) public confirmations;

    // Events

    event Deposit(address indexed sender, uint value);
    event Request(address indexed sender, uint indexed transactionId);
    event Confirmation(address indexed sender, uint indexed transationId);
    event Revocation(address indexed sender, uint indexed transactionId);
    event Execution(uint indexed transactionId);

    // Modifiers

    modifier onlyOwner() {
        require(isOwner[msg.sender]);
        _;
    }

    modifier transactionExists(uint _transactionId) {
        require(transactions[_transactionId].to != 0);
        _;
    }

    // Constructor

    constructor(string _name, address[] _owners, uint8 _required) public {
        require(_owners.length < MAX_OWNERS_COUNT);

        for (uint i = 0; i < _owners.length; i++) {
            require(_owners[i] != 0);
            isOwner[_owners[i]] = true;
        }

        name = _name;
        owners = _owners;
        required = _required;
    }

    // Fallback

    function() public payable {
        require(msg.value > 0);

        emit Deposit(msg.sender, msg.value);
    }

    // Methods

    function requestTransaction(address _to, uint256 _value, bytes _data) public
    onlyOwner
    returns (uint transactionId) {
        transactionId = addTransaction(_to, _value, _data);

        emit Request(msg.sender, transactionId);

        confirmTransaction(transactionId);
    }

    function confirmTransaction(uint _transactionId) public
    onlyOwner
    transactionExists(_transactionId) {
        confirmations[_transactionId][msg.sender] = true;

        emit Confirmation(msg.sender, _transactionId);

        executeTransaction(_transactionId);
    }

    function revokeTransaction(uint _transactionId) public
    onlyOwner
    transactionExists(_transactionId) {
        confirmations[_transactionId][msg.sender] = false;

        if (transactions[_transactionId].sender == msg.sender) {
            delete transactions[_transactionId];
            removePendingTransaction(_transactionId);
        }

        emit Revocation(msg.sender, _transactionId);
    }

    // Getters

    function getOwnersCount() public constant returns (uint count) {
        count = owners.length;
    }

    function getOwner(uint8 _index) public constant returns (address owner) {
        require(_index < owners.length);
        owner = owners[_index];
    }

    function getPendingsCount() public constant returns (uint count) {
        count = pendings.length;
    }

    function getPendingTransactionId(uint _index) public constant
    returns (uint transactionId) {
        transactionId = pendings[_index];
    }

    function getConfirmationsCount(uint _transactionId) public constant
    transactionExists(_transactionId)
    returns (uint count) {
        for (uint i = 0; i < owners.length; i++) {
            if (confirmations[_transactionId][owners[i]]) {
                count += 1;
            }
        }
    }

    function isConfirmed(uint _transactionId) public constant
    transactionExists(_transactionId)
    returns (bool) {
        return getConfirmationsCount(_transactionId) >= required;
    }

    // Internals

    function addTransaction(address _to, uint _value, bytes _data) internal
    returns (uint transactionId) {
        require(_to != 0);
        require(_value > 0);

        transactionId = nonce;

        transactions[transactionId] = Transaction(
            {sender : msg.sender, to : _to, value : _value, data : _data}
        );

        pendings.push(transactionId);

        nonce += 1;
    }

    function executeTransaction(uint _transactionId) internal {
        if (!isConfirmed(_transactionId)) {
            return;
        }

        Transaction storage transaction = transactions[_transactionId];

        address to = transaction.to;
        transactions[_transactionId].to = 0;

        removePendingTransaction(_transactionId);

        execute(to, transaction.value, transaction.data);

        emit Execution(_transactionId);

        delete transactions[_transactionId];
    }

    function removePendingTransaction(uint _transactionId) internal {
        for (uint i = 0; i < pendings.length; i++) {
            if (_transactionId == pendings[i]) {
                pendings[i] = pendings[pendings.length - 1];
                break;
            }
        }
        delete pendings[pendings.length - 1];
        pendings.length -= 1;
    }

    function execute(address _to, uint _value, bytes _data) internal {
        require(_to.call.value(_value)(_data));
    }
}
