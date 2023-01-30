import { useState, useCallback } from 'react';

export default function useForceUpdate() {
  const [, setState] = useState({});
  return useCallback(() => setState({}), []);
}
