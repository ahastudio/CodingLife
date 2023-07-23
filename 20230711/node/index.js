const Sentry = require('@sentry/node');

Sentry.init({
  dsn: 'https://xxx@xxx.ingest.sentry.io/xxx',
  tracesSampleRate: 1.0,
});

function bar() {
  a.x = 3;
}

function foo() {
  bar();
}

function main() {
  foo();
}

main();
