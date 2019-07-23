
const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const service = require('./service');

const PORT = 3000;

const { log } = console;

const app = express();

app.use(cors());
app.use(bodyParser.json());

app.get('/tasks', async (req, res) => {
  const tasks = await service.getTasks();
  res.send({ tasks });
});

app.post('/tasks', async (req, res) => {
  const { title } = req.body;
  await service.addTask(title);
  res.send({});
});

(async () => {
  await service.init();

  app.listen(PORT, () => {
    log(`Listen :${PORT}`);
  });
})();
