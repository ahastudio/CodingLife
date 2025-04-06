import { useState } from 'react';

import Postcode from './Postcode';

export default function Header() {
  const [visible, setVisible] = useState(false);

  const handleClick = () => {
    setVisible(!visible);
  };

  return (
    <header>
      <h1>Tickets</h1>
      {visible && (
        <Postcode />
      )}
      <p>
        <button onClick={handleClick}>
          {visible ? 'Hide' : 'Show'}
        </button>
      </p>
    </header>
  );
}
