import { Store, Action, STORE_PROPERTY_NAME } from './core';

@Store()
class MyStore {
  name = '';

  @Action()
  changeName(name: string) {
    this.name = name;
  }
}

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
