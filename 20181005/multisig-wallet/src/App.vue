<template>
    <div id="app">
        <div v-if="isReady">
            <router-view/>
        </div>
    </div>
</template>

<script>
  import * as Web3 from 'web3';

  import {
    MultiSigWalletFactory,
    initContracts,
  } from './utils/contracts';

  export default {
    name: 'app',
    data() {
      return {
        isReady: false,
      };
    },
    async created() {
      if (typeof(web3) === 'undefined') {
        alert('Provider not found!');
        return;
      }

      const provider = web3.currentProvider;
      window.web3 = new Web3(provider);

      try {
        initContracts(provider);

        const accounts = await web3.eth.getAccounts();
        this.$store.commit('setAccount', accounts[0]);

        const factory = await MultiSigWalletFactory.deployed();
        this.$store.commit('setWalletFactory', factory);
      } catch (e) {
        console.error(e);
        return;
      }

      this.isReady = true;
    },
  }
</script>

<style>
    #app {
        padding: 20px;
    }
</style>
