import { useEffect, useRef } from 'react';

export default function Postcode() {
  const containerRef = useRef(null);

  useEffect(() => {
    if (!containerRef.current) {
      return;
    }

    new daum.Postcode({
      oncomplete: (data) => {
        console.log(data);
      },
      width : '100%',
      height : '100%',
    }).embed(containerRef.current);
  }, [containerRef.current]);

  return (
    <div
      ref={containerRef}
      style={{
        width: '500px',
        height: '444px',
      }}
    ></div>
  );
}
