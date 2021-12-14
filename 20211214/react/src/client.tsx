import { StateClient } from '@hya/micro-state';

const { log } = console;

const client = new StateClient();

client.initialize('count', 13);
client.initialize('name', 'JOKER');

client.subscribe('count', () => {
  log({
    hasCount: client.has('count'),
    count: client.get('count'),
  });
});

export default client;
