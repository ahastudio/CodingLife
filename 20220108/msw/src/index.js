import axios from 'axios';

import './mocks';

const BASE_URL = 'https://example.com';

async function fetchPosts() {
  const { data } = await axios.get(`${BASE_URL}/posts`);
  console.log(data);
  console.log();
}

async function createPost() {
  const { data } = await axios.post(`${BASE_URL}/posts`);
  console.log(data);
  console.log();
}

async function main() {
  await fetchPosts();

  await [1, 2, 3].reduce((acc, cur) => {
    return acc.then(async () => {
      console.log(`<< ${cur} >>`);
      await createPost();
      await fetchPosts();
    });
  }, Promise.resolve());
}

main();
