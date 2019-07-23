const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const { getTasks, addTask } = require('./service');

const PORT = 3000;

const { log } = console;

const app = express();

app.use(cors());
app.use(bodyParser.json());

app.get('/tasks', (req, res) => {
  const tasks = getTasks();
  res.send({ tasks });
});

app.post('/tasks', (req, res) => {
  const { title } = req.body;
  addTask(title);
  res.send({});
});

app.listen(PORT, () => {
  log(`Listen :${PORT}`);
});
