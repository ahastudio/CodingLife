import server from './server';

const { log } = console;

async function main(): Promise<void> {
  const { url } = await server.listen({
    port: 3000,
  });
  log(`🚀  Server ready at ${url}`);
}

main();
