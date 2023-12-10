import net from "net";

const server = net.createServer((socket) => {
  console.log("Accept a new connection");

  socket.on("data", (data) => {
    const message = data.toString();

    console.log(`Received message: ${message}`);

    socket.write(message);
  });

  socket.on("end", () => {
    console.log("Close a connection");
  });
});

export default function runServer({ port }: { port: number }) {
  server.listen(port, () => {
    console.log("Server started:", server.address());
    console.log("Open a new window and type this:");
    console.log("$ nc localhost 3000");
  });
}
