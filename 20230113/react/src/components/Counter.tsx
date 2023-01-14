import useCountRef from '../hooks/useCountRef';
import useCounts from '../hooks/useCounts';

function temporaryKey(index: number) {
  return `key-${index}`;
}

export default function Counter() {
  const count = useCountRef();

  const { counts, increase } = useCounts(5);

  const handleClick = (index: number) => {
    count.current += 1;
    increase(index);
  };

  return (
    <div>
      <p>
        Count:
        {' '}
        {count.current}
      </p>
      <p>{JSON.stringify(counts)}</p>
      <p>
        {counts.map((_, index) => (
          <button
            key={temporaryKey(index)}
            type="button"
            onClick={() => handleClick(index)}
          >
            Click #
            {index + 1}
          </button>
        ))}
      </p>
    </div>
  );
}
