/* eslint-disable import/no-extraneous-dependencies */
/* eslint-disable @typescript-eslint/no-unused-vars */

import {
  beforeAll, afterAll,
  beforeEach, afterEach,
  describe, it, test,
  expect,
  vi,
} from 'vitest';

import { cleanup } from '@testing-library/react';

import matchers from '@testing-library/jest-dom/matchers';

import 'whatwg-fetch';

import 'reflect-metadata';

import server from './mocks/server';

expect.extend(matchers);

afterEach(() => cleanup());

beforeAll(() => server.listen({ onUnhandledRequest: 'error' }));

afterAll(() => server.close());

afterEach(() => server.resetHandlers());
