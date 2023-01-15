import { useEffect } from 'react';

import { useBoolean } from 'usehooks-ts';

function Timer() {
  useEffect(() => {
    const savedTitle = document.title;

    const id = setInterval(() => {
      document.title = `Now: ${new Date().getTime()}`;
    }, 100);

    return () => {
      document.title = savedTitle;
      clearInterval(id);
    };
  });

  return (
    <p>Playing</p>
  );
}

export default function TimerControl() {
  const { value: playing, toggle } = useBoolean(false);

  const handleClick = () => {
    toggle();
  };

  return (
    <div>
      {playing ? (
        <Timer />
      ) : (
        <p>Stop</p>
      )}
      <button type="button" onClick={handleClick}>
        Toggle
      </button>
    </div>
  );
}
