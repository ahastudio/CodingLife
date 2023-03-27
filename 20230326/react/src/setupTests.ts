// eslint-disable-next-line import/no-extraneous-dependencies
import '@testing-library/jest-dom';

import 'reflect-metadata';

import server from './mocks/server';

beforeAll(() => server.listen({ onUnhandledRequest: 'error' }));

afterAll(() => server.close());

afterEach(() => server.resetHandlers());
