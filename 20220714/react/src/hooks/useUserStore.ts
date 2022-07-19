import useStore from './useStore';

import UserStore from '../stores/UserStore';

const userStore = new UserStore();

export default function useUserStore(): UserStore {
  return useStore(userStore);
}
