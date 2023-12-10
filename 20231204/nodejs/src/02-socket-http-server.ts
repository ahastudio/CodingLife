import net from "net";

const server = net.createServer((socket) => {
  console.log("Accept a new connection");
  console.log("-".repeat(80));

  socket.on("data", (data) => {
    // Request ---------------------------------------------------------------

    const message = data.toString();

    const index = message.indexOf("\n");
    const startLine = message.substring(0, index);
    console.log(startLine);

    const [method, url, httpVersion] = startLine.split(/\s+/);
    console.log({ method, url, httpVersion });

    const separator = "\r\n\r\n";

    const contentIndex = message.indexOf(separator);
    const requestContent = message.substring(contentIndex + separator.length);
    console.log(requestContent);

    if (requestContent) {
      const requestBody = JSON.parse(requestContent);
      console.log(requestBody);
    }

    // Response --------------------------------------------------------------

    const content = "안녕? Hello, world!\n";
    const body = Buffer.from(content);

    // start-line
    socket.write("HTTP/1.1 200 OK");
    socket.write("\n");

    // headers
    const headers = [`Content-Length: ${body.length}`];
    socket.write(headers.join("\n"));
    socket.write("\n");

    // empty line
    socket.write("\n");

    // body
    socket.write(body);
  });

  socket.on("end", () => {
    console.log("-".repeat(80));
    console.log("Close a connection");
  });
});

export default function runServer({ port }: { port: number }) {
  server.listen(port, () => {
    console.log(`Server is listening on http://localhost:${port}/`);
  });
}
