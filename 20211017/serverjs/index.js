const server = require('server');

// https://serverjs.io/documentation/#cors
const corsExpress = require('cors')({
  origin: ['http://localhost:3000'],
});

// https://serverjs.io/#api-design
const router = require('./src/router');

const PORT = 8080;

const cors = server.utils.modern(corsExpress);

server({
  port: PORT,
  security: { csrf: false },
}, cors, router);
