require('dotenv').config();

const SmeeClient = require('smee-client');

const client = new SmeeClient({
  source: process.env.WEBHOOK_PROXY_URL,
  target: 'http://localhost:3000/events',
  logger: console,
});

client.start();
