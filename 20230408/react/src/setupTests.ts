/* eslint-disable import/no-extraneous-dependencies */

import '@testing-library/jest-dom';

import 'whatwg-fetch';

import 'reflect-metadata';

import server from './mocks/server';

beforeAll(() => server.listen({ onUnhandledRequest: 'error' }));

afterAll(() => server.close());

afterEach(() => server.resetHandlers());
