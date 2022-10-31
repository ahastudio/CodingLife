import { useStore } from 'usestore-ts';

import BoardStore from '../stores/BoardStore';

const boardStore = new BoardStore();

export default function useBoardStore() {
  return useStore(boardStore);
}
