import http from 'node:http';

const PORT = 8080;

const server = http.createServer((req, res) => {
  console.log('✅ Request received');

  const url = new URL(req.url || '/', `http://${req.headers.host}`);

  console.log('-'.repeat(80));
  console.log('Method:', req.method);
  console.log('Path:', url.pathname);
  console.log('Headers:', req.headers);

  const requestBody: any[] = [];

  req.on('data', (chunk: any) => {
    requestBody.push(chunk);
  })

  req.on('end', () => {
    const body = Buffer.concat(requestBody).toString();
    if (body) {
      console.log('Body:', body);
    }
    console.log('-'.repeat(80));

    res.writeHead(200, { 'Content-Type': 'application/json' });
    res.write(JSON.stringify({ message: 'Hello, world!' }));
    res.end();
  });
});

server.listen({
  host: '0.0.0.0',
  port: PORT,
}, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
