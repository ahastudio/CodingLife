import select from "@inquirer/select";

async function main() {
  const answer = await select({
    message: "Select a server",
    choices: [
      {
        name: "Echo Server (using Socket)",
        value: "01-socket-echo-server",
      },
      {
        name: "HTTP Server (using Socket)",
        value: "02-socket-http-server",
      },
      {
        name: "HTTP Server (using http module)",
        value: "03-nodejs-http-server",
      },
      {
        name: "HTTP Server (using Express)",
        value: "04-express-http-server",
      },
      {
        name: "HTTP Server (using Fastify)",
        value: "05-fastify-http-server",
      },
    ],
  });

  const { default: runServer } = await import(`./${answer}`);
  runServer({ port: 3000 });
}

main();
