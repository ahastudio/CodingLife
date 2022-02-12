import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    account: null,
    walletFactory: null,
  },
  mutations: {
    setAccount(state, account) {
      state.account = account;
    },
    setWalletFactory(state, factory) {
      state.walletFactory = factory;
    },
  },
  actions: {},
})
