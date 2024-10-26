// https://ko.vite.dev/config/
// https://github.com/vitejs/vite-plugin-react/tree/main/packages/plugin-react

import { defineConfig } from 'vite';

import react from '@vitejs/plugin-react';

export default defineConfig({
  plugins: [react()],
});
