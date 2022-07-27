import useStore from './useStore';

import { postStore } from '../stores/PostStore';

export default function usePostStore() {
  return useStore(postStore);
}
