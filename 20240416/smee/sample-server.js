const http = require('http');

const port = 3000;

const server = http.createServer((request, response) => {
  const body = [];

  request
    .on('data', (chunk) => {
      body.push(chunk);
    })
    .on('end', () => {
      const content = Buffer.concat(body).toString();
      console.log(content);
      console.log('-'.repeat(80));

      if (content) {
        const data = JSON.parse(content);
        console.log(data);
        console.log('-'.repeat(80));
      }

      response.writeHead(200, {'Content-Type': 'text/plain'});
      response.write('Hello, world!\n');
      response.end();
    });
});

server.listen(port);
