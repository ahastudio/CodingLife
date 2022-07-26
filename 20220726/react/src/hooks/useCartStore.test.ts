import { container } from 'tsyringe';

import { renderHook } from '@testing-library/react';

import useCartStore from './useCartStore';

import Cart from '../models/Cart';

import CartStore from '../stores/CartStore';

const cartStore = container.resolve(CartStore);

describe('useCartStore', () => {
  it('returns the snapshot and the store', () => {
    const { result } = renderHook(() => useCartStore());

    expect(result.current).toEqual([
      { cart: new Cart() },
      cartStore,
    ]);
  });
});
