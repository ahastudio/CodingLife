import { useCallback } from 'react';

import TextInput from './TextInput';

import useUserStore from '../hooks/useUserStore';

import log from '../utils/log';

export default function InputForm() {
  const [state, store] = useUserStore();

  log('<InputForm> - snapshot:', JSON.stringify(state));

  const handleChange = useCallback((value: string) => {
    store.changeName(value);
  }, [store]);

  return (
    <div>
      <TextInput
        name="name"
        type="text"
        field={state.name}
        onChange={handleChange}
      />
    </div>
  );
}
