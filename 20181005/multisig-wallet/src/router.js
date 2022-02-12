import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home';
import NewWallet from './views/NewWallet';
import Wallet from './views/Wallet';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/wallet/new',
      name: 'new-wallet',
      component: NewWallet,
    },
    {
      path: '/wallet/:address',
      name: 'wallet',
      component: Wallet,
    },
  ],
});
