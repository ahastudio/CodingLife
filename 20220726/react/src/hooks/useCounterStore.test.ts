import { renderHook } from '@testing-library/react';

import useCounterSotre, { counterStore } from './useCounterStore';

describe('useCounterSotre', () => {
  it('returns the snapshot and the store', () => {
    const { result } = renderHook(() => useCounterSotre());

    expect(result.current).toEqual([
      { count: 0 },
      counterStore,
    ]);
  });
});
