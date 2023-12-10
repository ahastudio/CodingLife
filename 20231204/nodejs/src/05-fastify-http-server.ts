import Fastify from "fastify";

const app = Fastify();

app.get("/", async (request) => {
  const { method, url, httpVersion } = request.raw;
  console.log({ method, url, httpVersion });

  const { headers } = request;
  console.log(headers);

  const { body } = request;
  console.log(body);

  return { message: "안녕? Hello, world!" };
});

app.post("/tasks", async (request, reply) => {
  const { method, url, httpVersion } = request.raw;
  console.log({ method, url, httpVersion });

  const { headers } = request;
  console.log(headers);

  const { body } = request;
  console.log(body);

  reply.code(201);
  return "Created!";
});

export default async function runServer({ port }: { port: number }) {
  await app.listen({ port });
  console.log(`Server is listening on http://localhost:${port}/`);
}
