import app from './app';
import { startServer } from './server';

const { log } = console;

async function main({ port }: {
  port: number;
}) {
  const { path } = await startServer({ app, port });

  log(`🚀  Server ready at http://localhost:${port}${path}`);
}

main({ port: 3000 });
