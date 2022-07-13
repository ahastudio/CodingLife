import TestServer from './TestServer';

const server = new TestServer();

export default async () => {
  const port = await server.start();

  process.env.API_BASE_URL = `http://localhost:${port}`;

  (globalThis as any).TEST_SERVER = server;
};
