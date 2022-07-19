/* eslint-disable max-classes-per-file */

export const STORE_GLUE_PROPERTY_NAME = '##store';

function areEqual(a: object, b: object) {
  const keys = Reflect.ownKeys(a);
  return keys.length === Reflect.ownKeys(b).length
    && keys.every((key) => Reflect.get(a, key) === Reflect.get(b, key));
}

export class StoreGlue {
  propertyKeys: string[];

  listeners = new Set<() => void>();

  snapshot: object = {};

  constructor(target: object) {
    this.propertyKeys = Reflect.ownKeys(target).map((i) => String(i))
      .filter((i) => !i.startsWith('#'));
  }

  subscribe(onChange: () => void): () => void {
    this.listeners.add(onChange);
    return () => {
      this.listeners.delete(onChange);
    };
  }

  getSnapshot(): object {
    return this.snapshot;
  }

  updateSnapshot(target: object): void {
    const snapshot = this.propertyKeys.reduce((acc, key) => ({
      ...acc,
      [key]: Reflect.get(target, key),
    }), {});
    if (!areEqual(snapshot, this.snapshot)) {
      this.snapshot = snapshot;
      this.listeners.forEach((listener) => listener());
    }
  }
}

// eslint-disable-next-line @typescript-eslint/no-explicit-any, @typescript-eslint/ban-types
type Klass = { new (...args: any[]): {} };

export function Store() {
  return function decorator<T extends Klass>(klass: T) {
    return class extends klass {
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      constructor(...args: any[]) {
        super(...args);
        const glue = new StoreGlue(this);
        Reflect.set(this, STORE_GLUE_PROPERTY_NAME, glue);
        glue.updateSnapshot(this);
      }
    };
  };
}

export function Action() {
  return (
    target: object,
    propertyKey: string,
    descriptor: PropertyDescriptor,
  ) => {
    const method = descriptor.value;
    // eslint-disable-next-line no-param-reassign
    descriptor.value = function decorator(...args: unknown[]) {
      const returnValue = method.apply(this, args);
      Reflect.get(this, STORE_GLUE_PROPERTY_NAME).updateSnapshot(this);
      return returnValue;
    };
  };
}
