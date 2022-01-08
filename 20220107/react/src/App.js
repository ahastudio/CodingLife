import useSWR from 'swr';
import Chance from 'chance';
import { loremIpsum } from 'lorem-ipsum';

import logo from './logo.svg';
import './App.css';

const fetcher = (url) => fetch(url).then((response) => response.json());

async function requestPost(url, data) {
  await fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
}

function App() {
  const url = 'http://localhost:3000/posts';
  const { data: posts, mutate } = useSWR(url, fetcher);

  if (!posts) {
    return <p>Loading...</p>;
  }

  async function addPost() {
    const chance = new Chance();
    await requestPost(url, {
      author: chance.name(),
      title: chance.animal(),
      body: loremIpsum(),
    });
    mutate();
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
        <h1>Posts</h1>
        <ul>
          {posts.map((post) => (
            <li key={post.id}>
              {post.title}
              {' '}
              by {post.author}
            </li>
          ))}
        </ul>
        <button type="button" onClick={addPost}>
          게시물 추가
        </button>
      </header>
    </div>
  );
}

export default App;
