import * as dotenv from 'dotenv';

import app from './src/app';

import './src/routes';

dotenv.config();

const { log } = console;

const port = process.env.PORT || 3000;

app.listen(port, () => {
  log(`Server running at http://localhost:${port}`);
});
