import React from 'react';

import { RecoilRoot } from 'recoil';

import TodoServiceContext, { todoService } from './contexts/TodoServiceContext';

import StatesUpdater from './components/StatesUpdater';
import Main from './components/Main';

const { log } = console;

export default function App() {
  log('App');

  return (
    <RecoilRoot>
      <TodoServiceContext.Provider value={todoService}>
        <StatesUpdater />
        <React.StrictMode>
          <Main />
        </React.StrictMode>
      </TodoServiceContext.Provider>
    </RecoilRoot>
  );
}
