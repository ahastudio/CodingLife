if (process.env.NODE_ENV !== 'production') {
  require('dotenv').config();
}

const ENDPOINT_URL = process.env.ENDPOINT_URL;
const INTERVAL_SECONDS = process.env.INTERVAL_SECONDS;

function getNow(date) {
  const now = new Date();

  const options = {
    timeZone: 'Asia/Seoul',
    year: 'numeric',
    month: '2-digit',
    day: '2-digit',
    hour: '2-digit',
    minute: '2-digit',
    second: '2-digit',
    hour12: true,
  };

  const formatter = new Intl.DateTimeFormat('ko-KR', options);

  return formatter.format(now);
}

async function step() {
  console.log();
  console.log('-'.repeat(80));
  console.log(getNow());
  console.log('-'.repeat(80));

  const response = await fetch(ENDPOINT_URL);
  console.log(response);
}

async function main() {
  step();
  setInterval(step, INTERVAL_SECONDS * 1_000);
}

main();
