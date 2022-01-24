import { useState } from 'react';

const listeners: { [name: string]: Set<Function> } = {};

let state: { [name: string]: any } = {};

export default function usePubSub() {
  const [, update] = useState<any>();

  return {
    subscribe(key: string) {
      if (!listeners[key]) {
        listeners[key] = new Set();
      }
      listeners[key].add(update);
    },
    unsubscribe(key: string) {
      listeners[key].delete(update);
    },
    publish(key: string, value: any) {
      if (!listeners[key]) {
        return;
      }
      listeners[key].forEach((listener) => {
        state = { ...state, [key]: value };
        listener(state);
      });
    },
    value(key: string) {
      return state[key];
    },
  };
}
