import net from 'node:net';

const socket = net.createConnection({
  host: 'example.com',
  port: 80,
});

socket.on('connect', () => {
  console.log('✅ Connected');

  const startLine = 'GET / HTTP/1.1'
  const headers = ['Host: example.com'];
  const body = '';

  const message = [
    startLine,
    ...headers,
    '',
    body,
  ].join('\r\n');

  console.log('🚀 Sending');
  console.log('-'.repeat(80));
  console.log(message);
  console.log('-'.repeat(80));

  socket.write(message);
});

socket.on('data', (data: Buffer) => {
  console.log('✅ Received');
  console.log('-'.repeat(80));
  console.log(data.toString());
  console.log('-'.repeat(80));

  socket.destroy();
});

socket.on('close', (hadError: boolean) => {
  console.log('✅ Connection closed - error?:', hadError);
});
