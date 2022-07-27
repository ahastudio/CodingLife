import useStore from './useStore';

import { postFormStore } from '../stores/PostFormStore';

export default function usePostFormStore() {
  return useStore(postFormStore);
}
