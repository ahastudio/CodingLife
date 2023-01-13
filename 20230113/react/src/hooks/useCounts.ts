import { useEffect, useState } from 'react';

export default function useCounts(size: number) {
  const [counts, setCounts] = useState(Array(size).fill(0));

  useEffect(() => {
    console.log(`Effect: ${counts[0]}`);
  }, [counts[0]]);

  return {
    // States
    counts,
    // Modifiers
    increase(index: number) {
      setCounts([
        ...counts.slice(0, index),
        counts[index] + 1,
        ...counts.slice(index + 1),
      ]);
    },
  };
}
