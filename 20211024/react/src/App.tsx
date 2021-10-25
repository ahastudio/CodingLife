import { Store } from './store';

import Greeting from './components/Greeting';
import NameField from './components/NameField';
import CounterPanel from './components/CounterPanel';
import CounterButton from './components/CounterButton';

export default function App() {
  return (
    <Store>
      <Greeting />
      <NameField />
      <CounterPanel />
      <CounterButton />
    </Store>
  );
}
