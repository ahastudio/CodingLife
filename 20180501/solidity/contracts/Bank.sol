pragma solidity ^0.4.21;

import "openzeppelin-solidity/contracts/token/ERC20/ERC20.sol";

contract Bank {
    address public tokenAddress;
    uint public tokenAmount;

    function deposite(address _tokenAddress, uint _amount) public {
        require(tokenAddress == address(0));
        require(_tokenAddress != address(0));

        ERC20 token = ERC20(_tokenAddress);

        require(token.transferFrom(msg.sender, address(this), _amount));

        tokenAddress = _tokenAddress;
        tokenAmount = _amount;
    }

    function withdraw() public {
        require(tokenAddress != address(0));

        ERC20 token = ERC20(tokenAddress);

        require(token.transfer(msg.sender, tokenAmount) == true);

        tokenAddress = address(0);
        tokenAmount = 0;
    }
}
