import {
  STORE_GLUE_PROPERTY_NAME, StoreGlue, Store, Action,
} from './core';

@Store()
class MyStore {
  name = '';

  @Action()
  changeName(name: string) {
    this.name = name;
  }
}

const context = describe;

test('StoreGlue', () => {
  const name = 'Peter Parker';
  const handleChange = jest.fn();

  const target = {
    name,
  };

  const glue = new StoreGlue(target);

  glue.subscribe(handleChange);

  glue.updateSnapshot(target);

  const snapshot = {
    name,
  };

  expect(handleChange).toBeCalled();

  expect(glue.getSnapshot()).toEqual(snapshot);
});

describe('@Action', () => {
  const name = 'Peter Parker';
  const handleChange = jest.fn();

  let store: MyStore;
  let glue: StoreGlue;

  beforeEach(() => {
    jest.clearAllMocks();

    store = new MyStore();

    glue = Reflect.get(store, STORE_GLUE_PROPERTY_NAME);
    glue.subscribe(handleChange);
  });

  context('with different state', () => {
    it('calls onChange handler', () => {
      store.changeName(name);

      expect(store.name).toBe(name);

      expect(handleChange).toBeCalled();

      expect(glue.getSnapshot()).toEqual({
        name,
      });
    });
  });

  context('with same state', () => {
    it("doesn't calls onChange handler", () => {
      store.changeName(store.name);

      expect(handleChange).not.toBeCalled();
    });
  });
});
