<template>
  <div class="wallets">
    <h1>Wallets</h1>
    <p>Total: {{ wallets.length }}</p>
    <ul class="wallets-list list-group">
      <li
        v-for="wallet in wallets"
        :key="wallet.address"
        class="list-group-item"
      >
        <router-link
          :to="{ name: 'wallet', params: { address: wallet.address }}"
        >
          {{ wallet.name }}
          <small>{{ wallet.address }}</small>
        </router-link>
      </li>
    </ul>
    <hr>
    <router-link
      class="btn btn-primary"
      :to="{ name: 'new-wallet' }"
    >
      Create New Wallet
    </router-link>
  </div>
</template>

<script>
import { mapState } from 'vuex';

import { MultiSigWallet } from '../utils/contracts';

export default {
  name: 'WalletList',
  data() {
    return {
      name: 'test',
      wallets: [],
    };
  },
  computed: mapState([
    'account',
    'walletFactory',
  ]),
  async created() {
    this.loadWallets();
  },
  methods: {
    async loadWallets() {
      try {
        const factory = this.walletFactory;
        const count = (await factory.getWalletsCount()).toNumber();
        this.wallets = await Promise.all(
          [...Array(count).keys()].map(async (i) => {
            const address = await factory.getWallet(i);
            const contract = await MultiSigWallet.at(address);
            const name = await contract.getName();
            return { contract, address, name };
          }),
        );
      } catch (e) {
        console.error(e);
      }
    },
  },
};
</script>

<style scoped>
    .wallets-list a {
        display: block;
    }
</style>
