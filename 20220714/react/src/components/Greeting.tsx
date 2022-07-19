import { useCallback } from 'react';

import TextInput from './TextInput';

import useUserStore from '../hooks/useUserStore';

export default function Greeting() {
  const userStore = useUserStore();

  const handleChange = useCallback((value: string) => {
    userStore.changeName(value);
  }, [userStore]);

  return (
    <div>
      <p>
        Hello,
        {' '}
        {userStore.name.value || 'world'}
        !
      </p>
      <TextInput
        name="name"
        type="text"
        field={userStore.name}
        onChange={handleChange}
      />
    </div>
  );
}
