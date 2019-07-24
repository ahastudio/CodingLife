const app = require('./app');

const PORT = 3000;

const { log } = console;

app.listen(PORT, () => {
  log(`App listening on port ${PORT}`);
});
