/* eslint-disable import/no-extraneous-dependencies */

import { esbuildPlugin } from '@web/dev-server-esbuild';

export default {
  rootDir: '.',
  appIndex: 'index.html',
  nodeResolve: true,
  plugins: [esbuildPlugin({ tsx: true })],
};
