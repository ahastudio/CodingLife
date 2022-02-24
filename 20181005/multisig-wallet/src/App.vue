<template>
  <div id="app">
    <div v-if="isReady">
      <router-view />
    </div>
  </div>
</template>

<script>
import detectEthereumProvider from '@metamask/detect-provider';

import {
  MultiSigWalletFactory,
  initContracts,
} from './utils/contracts';

export default {
  name: 'App',
  data() {
    return {
      isReady: false,
    };
  },
  async created() {
    const provider = await detectEthereumProvider();

    if (!provider) {
      alert('MetaMask is not installed!');
      return;
    }

    const accounts = await ethereum.request({ method: 'eth_accounts' });
    if (accounts.length) {
      this.init({ provider, accounts });
      return;
    }

    ethereum.request({ method: 'eth_requestAccounts' });
    ethereum.on('accountsChanged', (accounts) => {
      this.init({ provider, accounts });
    });
  },
  methods: {
    async init({ provider, accounts }) {
      initContracts(provider);

      this.$store.commit('setAccount', accounts[0]);

      const factory = await MultiSigWalletFactory.deployed();
      this.$store.commit('setWalletFactory', factory);

      this.isReady = true;
    },
  },
};
</script>

<style>
    #app {
        padding: 20px;
    }
</style>
