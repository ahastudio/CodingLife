import { useEffect } from 'react';

import useSelector from '../hooks/useSelector';

export default function NameCard() {
  const name = useSelector((state) => state.name);

  useEffect(() => {
    console.log('render NameCard');
  });

  return (
    <div>
      <p>
        Name:
        {' '}
        {name}
      </p>
    </div>
  );
}
