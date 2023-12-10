import express from "express";
import bodyParser from "body-parser";

const app = express();

app.use(bodyParser.json());

app.get("/", (req, res) => {
  const { method, url, httpVersion } = req;
  console.log({ method, url, httpVersion });

  const { headers } = req;
  console.log(headers);

  const { body } = req;
  console.log(body);

  res.send({ message: "안녕? Hello, world!" });
});

app.post("/tasks", (req, res) => {
  const { method, url, httpVersion } = req;
  console.log({ method, url, httpVersion });

  const { headers } = req;
  console.log(headers);

  const { body } = req;
  console.log(body);

  res.status(201).send("Created!");
});

export default function runServer({ port }: { port: number }) {
  app.listen(port, () => {
    console.log(`Server is listening on http://localhost:${port}/`);
  });
}
