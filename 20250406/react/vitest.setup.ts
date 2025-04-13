import { afterEach, vi } from 'vitest';

import { cleanup } from '@testing-library/react';

vi.mock('next/cache');

afterEach(() => {
  cleanup();
});
