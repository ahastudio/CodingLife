const { useState } = React;

const Welcome = () => (
  <p>Hello, world!</p>
);

const Counter = () => {
  const [count, setCount] = useState(0);
  return (
    <p>
      {count}
      {' '}
      <button type="button" onClick={() => setCount(count + 1)}>
        UP
      </button>
      {' '}
      <button type="button" onClick={() => setCount(count - 1)}>
        DOWN
      </button>
    </p>
  );
};

const App = () => (
  <>
    <header>
      <h1>React Simple Demo</h1>
    </header>
    <main>
      <Welcome />
      <Counter />
    </main>
  </>
);

ReactDOM.render(<App />, document.getElementById('app'));
