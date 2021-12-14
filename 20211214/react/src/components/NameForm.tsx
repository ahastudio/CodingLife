import { FormEvent, useRef } from 'react';

import { useMicroState } from '@hya/micro-state';

const { log } = console;

export default function NameForm() {
  log('<NameForm>');

  const refInput = useRef<HTMLInputElement>(null);

  const [, setName] = useMicroState('name', '');

  const handleSubmit = (event: FormEvent) => {
    event.preventDefault();
    if (!refInput.current) {
      return;
    }
    setName(refInput.current.value);
  };

  return (
    <form onSubmit={handleSubmit}>
      <p>
        <input type="text" ref={refInput} />
        <button type="submit">
          Submit
        </button>
      </p>
    </form>
  );
}
