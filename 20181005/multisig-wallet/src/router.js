import Vue from 'vue';
import Router from 'vue-router';

import HomePage from './views/HomePage';
import NewWalletPage from './views/NewWalletPage';
import WalletPage from './views/WalletPage';

Vue.use(Router);

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomePage,
    },
    {
      path: '/wallet/new',
      name: 'new-wallet',
      component: NewWalletPage,
    },
    {
      path: '/wallet/:address',
      name: 'wallet',
      component: WalletPage,
    },
  ],
});
