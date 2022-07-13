import { renderHook, waitFor } from '@testing-library/react';

import usePosts from './usePosts';

test('usePosts', async () => {
  const { result } = renderHook(() => usePosts());

  await waitFor(() => {
    expect(result.current).toHaveLength(2);
    expect(result.current.map((i) => i.id)).toEqual([1, 2]);
  });
});
