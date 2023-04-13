import { waitFor } from '@testing-library/react';
import OrderDetailStore from './OrderDetailStore';

import fixtures from '../../fixtures';

const fetchOrder = jest.fn();

jest.mock('../services/ApiService', () => ({
  get apiService() {
    return {
      fetchOrder,
    };
  },
}));

const context = describe;

describe('OrderDetailStore', () => {
  let store: OrderDetailStore;

  beforeEach(() => {
    jest.clearAllMocks();

    store = new OrderDetailStore();
  });

  describe('fetchOrder', () => {
    context('when fetching is successful', () => {
      const [order] = fixtures.orders;

      beforeEach(() => {
        fetchOrder.mockResolvedValue(order);
      });

      it('sets order data', async () => {
        store.fetchOrder({ orderId: order.id });

        expect(store.loading).toBeTruthy();

        await waitFor(() => {
          expect(store.order).toEqual(order);
          expect(store.loading).toBeFalsy();
          expect(store.error).toBeFalsy();
        });
      });
    });

    context('when fetching is failed', () => {
      beforeEach(() => {
        fetchOrder.mockRejectedValue('');
      });

      it('sets error', async () => {
        store.fetchOrder({ orderId: 'ORDER-iD' });

        expect(store.loading).toBeTruthy();

        await waitFor(() => {
          expect(store.order.id).toBeFalsy();
          expect(store.loading).toBeFalsy();
          expect(store.error).toBeTruthy();
        });
      });
    });
  });
});
