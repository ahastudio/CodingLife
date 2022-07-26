import { container } from 'tsyringe';

import { renderHook } from '@testing-library/react';

import useCounterSotre from './useCounterStore';

import CounterStore from '../stores/CounterStore';

const counterStore = container.resolve(CounterStore);

describe('useCounterSotre', () => {
  it('returns the snapshot and the store', () => {
    const { result } = renderHook(() => useCounterSotre());

    expect(result.current).toEqual([
      { count: 0 },
      counterStore,
    ]);
  });
});
