/* eslint-disable import/prefer-default-export */

// eslint-disable-next-line import/no-extraneous-dependencies
import { setHeadlessWhen, setCommonPlugins } from '@codeceptjs/configure';

// turn on headless mode when running with HEADLESS=true environment variable
// export HEADLESS=true && npx codeceptjs run
setHeadlessWhen(process.env.HEADLESS);

// enable all common plugins https://github.com/codeceptjs/configure#setcommonplugins
setCommonPlugins();

export const config = {
  name: 'shop-demo',
  tests: './tests/**/*_test.ts',
  output: './output',
  helpers: {
    Playwright: {
      url: 'http://localhost:8000',
      show: true,
      browser: 'chromium',
      waitForTimeout: 10_000,
    },
  },
  include: {
    I: './tests/steps_file.ts',
    backdoor: './tests/backdoor.ts',
  },
  plugins: {
    retryFailedStep: {
      enabled: true,
    },
  },
};
