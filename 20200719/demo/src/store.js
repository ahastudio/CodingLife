import { createStore } from 'redux';

import reducer from './slice';

const store = createStore(reducer);

export default store;
