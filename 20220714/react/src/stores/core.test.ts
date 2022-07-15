import {
  STORE_PROPERTY_NAME, ExternalStore, Store, Action,
} from './core';

@Store()
class MyStore {
  name = '';

  @Action()
  changeName(name: string) {
    this.name = name;
  }
}

test('ExternalStore', () => {
  const name = 'Peter Parker';
  const handleChange = jest.fn();

  const target = {
    name,
  };

  const externalStore = new ExternalStore(target);

  externalStore.subscribe(handleChange);

  externalStore.updateSnapshot();

  const snapshot = {
    name,
  };

  expect(handleChange).toBeCalledWith(snapshot);

  expect(externalStore.getSnapshot()).toEqual(snapshot);
});

test('@Action', () => {
  const name = 'Peter Parker';
  const handleChange = jest.fn();

  const myStore = new MyStore();

  const externalStore = Reflect.get(myStore, STORE_PROPERTY_NAME);

  externalStore.subscribe(handleChange);

  myStore.changeName(name);

  expect(myStore.name).toBe(name);

  const snapshot = {
    name,
  };

  expect(handleChange).toBeCalledWith(snapshot);

  expect(externalStore.getSnapshot()).toEqual(snapshot);
});
