import { ChangeEvent, useRef } from 'react';

import { ulid } from 'ulid';

import { useStore } from '../store';

export default function NameField() {
  const id = useRef(`input-${ulid()}`);

  const [name, setName] = useStore.name();

  const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
    const { value } = e.target;
    setName(value);
  };

  return (
    <p>
      <label htmlFor={id.current}>
        이름:
        {' '}
      </label>
      <input
        id={id.current}
        type="text"
        name="name"
        value={name}
        onChange={handleChange}
      />
    </p>
  );
}
