import { container } from 'tsyringe';

import { renderHook } from '@testing-library/react';

import useCounterSotre from './useCounterStore';

import CounterStore from '../stores/CounterStore';

describe('useCounterSotre', () => {
  beforeEach(() => {
    container.clearInstances();
  });

  it('returns the snapshot and the store', () => {
    const counterStore = container.resolve(CounterStore);

    const { result } = renderHook(() => useCounterSotre());

    expect(result.current).toEqual([
      { count: 0 },
      counterStore,
    ]);
  });
});
