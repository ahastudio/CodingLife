export default () => {
  const server = (globalThis as any).TEST_SERVER;

  server.close();
};
