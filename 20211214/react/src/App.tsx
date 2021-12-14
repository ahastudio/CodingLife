import { StateClientProvider } from '@hya/micro-state';

import client from './client';

import Counter from './components/Counter';
import Name from './components/Name';
import NameForm from './components/NameForm';

export default function App() {
  return (
    <StateClientProvider client={client}>
      <div>
        <h1>Hello, world!</h1>
        <Counter />
        <Name />
        <NameForm />
      </div>
    </StateClientProvider>
  );
}
