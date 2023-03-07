import { renderHook } from '@testing-library/react';

import useFilterTickets from './useFilterTickets';

test('useFilterTickets', () => {
  const { result } = renderHook(() => useFilterTickets());

  const filterTickets = result.current;

  const tickets = filterTickets({ status: 'todo' });

  expect(tickets).toHaveLength(5);
});
