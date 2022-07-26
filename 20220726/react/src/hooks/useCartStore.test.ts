import { renderHook } from '@testing-library/react';

import useCartStore, { cartStore } from './useCartStore';

import Cart from '../models/Cart';

describe('useCartStore', () => {
  it('returns the snapshot and the store', () => {
    const { result } = renderHook(() => useCartStore());

    expect(result.current).toEqual([
      { cart: new Cart() },
      cartStore,
    ]);
  });
});
