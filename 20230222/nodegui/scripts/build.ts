/* eslint-disable import/no-extraneous-dependencies */

import path from 'path';
import fs from 'fs';

import esbuild from 'esbuild';

const srcPath = path.resolve(__dirname, '../src');
const distPath = path.resolve(__dirname, '../dist');

if (!fs.existsSync(distPath)) {
  fs.mkdirSync(distPath);
}

esbuild.build({
  bundle: true,
  entryPoints: [srcPath],
  outfile: path.resolve(distPath, './index.js'),
  platform: 'node',
  loader: {
    '.node': 'file',
  },
  define: Object.keys(process.env)
    .filter((i) => i.startsWith('APP_'))
    .reduce((acc, key) => ({
      ...acc,
      [`process.env.${key}`]: `"${process.env[key]}"`,
    }), {}),
});
