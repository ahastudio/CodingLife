/* eslint-disable no-console, @typescript-eslint/no-empty-function */

const log = (process.env.NODE_ENV === 'development') ? console.log : () => {};

export default log;
