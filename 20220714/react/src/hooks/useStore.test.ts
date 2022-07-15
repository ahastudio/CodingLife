import { renderHook } from '@testing-library/react';

import useStore from './useStore';

import { Store } from '../stores/core';

const context = describe;

@Store()
class MyStore {
  name = 'Peter';
}

describe('useStore', () => {
  context('with correct store', () => {
    it('returns snapshot of store', () => {
      const store = new MyStore();

      const { result } = renderHook(() => useStore(store));

      expect(result.current).toEqual({
        name: 'Peter',
      });
    });
  });

  context('with incorrect store', () => {
    let consoleError: any;

    beforeEach(() => {
      // eslint-disable-next-line no-console
      consoleError = console.error;
      // eslint-disable-next-line no-console
      console.error = jest.fn();
    });

    afterEach(() => {
      // eslint-disable-next-line no-console
      console.error = consoleError;
    });

    it('throws error', () => {
      const store = {};

      expect(() => {
        renderHook(() => useStore(store));
      }).toThrow();
    });
  });
});
