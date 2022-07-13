import { renderHook, act } from '@testing-library/react';

import { StoreProvider } from '../wrappers';

import useCounter from './useCounter';

test('useCounter', () => {
  const { result } = renderHook(() => useCounter(), {
    wrapper: StoreProvider,
  });

  expect(result.current.count).toBe(0);

  act(() => {
    result.current.increase();
  });

  expect(result.current.count).toBe(1);

  act(() => {
    result.current.increase10();
  });

  expect(result.current.count).toBe(11);
});
