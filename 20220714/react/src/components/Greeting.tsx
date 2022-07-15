import useUserStore from '../hooks/useUserStore';

import TextInput from './TextInput';

export default function Greeting() {
  const userStore = useUserStore();

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
        onChange={(value) => userStore.changeName(value)}
      />
    </div>
  );
}
