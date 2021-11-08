import createStore from 'teaful';
import Counter from './models/Counter';

const initialState = {
  name: '',
  counter: new Counter(0),
};

// TODO: Remove `undefined`. It's bug of Teaful's `index.d.ts` file.
export const { useStore } = createStore(initialState, undefined);

// TODO: Delete next line!
export default {};
