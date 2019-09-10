exports.config = {
  tests: './*.test.js',
  output: './output',
  helpers: {
    Puppeteer: {
      url: 'http://localhost',
      show: true,
      windowSize: '1024x768',
      chrome: {
        args: ['--window-size=1024,768'],
      },
    },
  },
  include: {
    I: './steps_file.js',
  },
  bootstrap: null,
  mocha: {},
  name: 'codeceptjs',
};
