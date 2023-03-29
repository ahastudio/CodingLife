import app from './src/app';

const { log } = console;

const port = 3000;

app.listen(port, () => {
  log(`Server running at http://localhost:${port}`);
});
