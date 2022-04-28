const { log } = console;

const fastify = require('fastify')({
  logger: true,
});

fastify.register(require('@fastify/cors'));
fastify.register(require('@fastify/multipart'));

fastify.get('/', async (request) => {
  const query = { ...request.query };

  log('\n🚀 GET /👉', query);
  log('🔥🔥🔥\n');

  return {
    hello: 'world',
  };
});

fastify.post('/', async (request, reply) => {
  const data = await request.file();
  const { image } = data.fields;
  const { fields, ...rest } = image;

  log('\n🚀 POST / 👉', rest);

  const blob = await image.file.read();
  log('✅ Data:', blob);

  log('🔥🔥🔥\n');

  reply.send({
    Points: '10,10;30,20;20,30',
  });
});

async function main() {
  try {
    await fastify.listen(3000);
  } catch (err) {
    fastify.log.error(err);
    process.exit(1);
  }
}

main();
