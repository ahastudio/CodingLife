import app from '../app';

import { resetData } from '../data';

app.get('/backdoor/setup-database', (req, res) => {
  resetData();

  res.send('OK');
});

export default app;
