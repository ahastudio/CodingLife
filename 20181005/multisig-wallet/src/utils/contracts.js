import contract from '@truffle/contract';

import MultiSigWalletFactoryArtifacts
  from '../../build/contracts/MultiSigWalletFactory.json';
import MultiSigWalletArtifacts
  from '../../build/contracts/MultiSigWallet.json';

const MultiSigWalletFactory = contract(MultiSigWalletFactoryArtifacts);
const MultiSigWallet = contract(MultiSigWalletArtifacts);

export function initContracts(provider) {
  MultiSigWalletFactory.setProvider(provider);
  MultiSigWallet.setProvider(provider);
}

export {
  MultiSigWalletFactory,
  MultiSigWallet,
};
