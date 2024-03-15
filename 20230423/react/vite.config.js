/* eslint-disable import/no-extraneous-dependencies */

import { defineConfig, loadEnv } from 'vite';

import react from '@vitejs/plugin-react';

export default defineConfig(({ mode }) => {
  const env = loadEnv(mode, process.cwd(), '');
  return {
    define: {
      'process.env.API_BASE_URL': JSON.stringify(env.API_BASE_URL),
      'process.env.BACKDOOR_BASE_URL': JSON.stringify(env.BACKDOOR_BASE_URL),
    },
    plugins: [react()],
    test: {
      globals: true,
      environment: 'happy-dom',
      setupFiles: [
        '/src/setupTests.ts',
      ],
      coverage: {
        reporter: ['text', 'json', 'html'],
      },
    },
  };
});
