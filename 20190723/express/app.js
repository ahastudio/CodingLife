const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const { getTasks, addTask } = require('./service');

const app = express();

app.use(cors());
app.use(bodyParser.json());

app.get('/tasks', (req, res) => {
  const tasks = getTasks();
  res.send({ tasks });
});

app.post('/tasks', (req, res) => {
  const { title } = req.body;
  if (!title) {
    res.status(400).send({});
    return;
  }
  addTask(title);
  res.status(201).send({});
});

module.exports = app;
