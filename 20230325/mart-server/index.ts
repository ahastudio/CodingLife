import app from './src/app';

const PORT = process.env.PORT || 3000;

const { log } = console;

app.listen(PORT, () => {
  log(`server is running on http://localhost:${PORT}`);
});
