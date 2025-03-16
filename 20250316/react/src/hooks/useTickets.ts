import { useReducer } from 'react';

import reducer, { initialState } from '../reducer';

export default function useTickets() {
  const [tickets, dispatch] = useReducer(reducer, initialState);

  return { tickets, dispatch };
}
