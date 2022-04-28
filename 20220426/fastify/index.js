const { log } = console;

function readStream(stream) {
  return new Promise((resolve, reject) => {
    const chunks = [];
    stream.on('data', (chunk) => chunks.push(chunk));
    stream.on('end', () => resolve(Buffer.concat(chunks)));
    stream.on('error', (err) => reject(err));
  });
}

//

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
  if (!request.isMultipart()) {
    log('😵');
    reply.send();
    return;
  }

  const data = await request.file();
  const { image } = data.fields;
  const { fields, ...rest } = image;

  log('\n🚀 POST / 👉', rest);

  const blob = await readStream(image.file);
  log('✅ Data:', blob);
  log('✅ Data Size:', blob.length);

  log('🔥🔥🔥\n');

  reply.send('Hello, world!');
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
