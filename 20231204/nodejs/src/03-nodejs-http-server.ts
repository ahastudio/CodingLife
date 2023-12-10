import http from "http";

const server = http.createServer((req, res) => {
  const { method, url, httpVersion } = req;
  console.log({ method, url, httpVersion });

  const { headers } = req;
  console.log(headers);

  const chunks: Buffer[] = [];

  req.on("data", (data) => {
    chunks.push(data);
  });

  req.on("end", () => {
    const body = Buffer.concat(chunks).toString();
    console.log(body);

    res.end("안녕? Hello, world!\n");
  });
});

export default function runServer({ port }: { port: number }) {
  server.listen(port, () => {
    console.log(`Server is listening on http://localhost:${port}/`);
  });
}
