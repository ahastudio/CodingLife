import { useState } from 'react';

import { useEventListener, useFetch } from 'usehooks-ts';

import { Cart } from '../types';

declare global {
  interface WindowEventMap {
    'reload-cart': CustomEvent;
  }
}

const baseUrl = process.env.API_BASE_URL || 'http://localhost:3000';

export default function useFetchCart() {
  const [nonce, setNonce] = useState(0);

  const url = `${baseUrl}/cart-items?${nonce}`;

  const { data, error } = useFetch<Cart>(url);

  useEventListener('reload-cart', () => {
    setNonce(nonce + 1);
  });

  return {
    cart: data ?? { cartItems: [], totalPrice: 0 },
    loading: !data,
    error,
  };
}
