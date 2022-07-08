/* eslint-disable no-console */

const fastify = require('fastify')();

fastify.get('/', async () => 'Hello, world!\n');

fastify.post('/echo', async (request) => {
  const data = Buffer.from(request.body || '');
  return data.toString();
});

async function main() {
  await fastify.listen({ host: '0.0.0.0', port: 3000 });
}

main();
