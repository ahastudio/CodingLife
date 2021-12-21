import { combineReducers, configureStore } from '@reduxjs/toolkit';

import counterReducer from './redux/counterSlice';
import { restaurantApi } from './services/restaurant';

export const rootReducer = combineReducers({
  counter: counterReducer,
  [restaurantApi.reducerPath]: restaurantApi.reducer,
});

export function setupStore() {
  return configureStore({
    reducer: rootReducer,
    middleware: (getDefaultMiddleware) => (
      getDefaultMiddleware().concat(restaurantApi.middleware)
    ),
  });
}
