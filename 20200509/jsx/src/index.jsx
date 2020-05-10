/* eslint-disable no-console, no-unused-vars, react/react-in-jsx-scope */

/* @jsx createElement */

function createNode(obj) {
  if (obj instanceof HTMLElement || obj instanceof Node) {
    return obj;
  }
  return document.createTextNode(obj);
}

function createElement(component, props, ...children) {
  // console.log('[IN]', component, props, ...children);

  const element = document.createElement(component, props);

  Object.entries(props || {}).forEach(([key, value]) => {
    element[key.toLowerCase()] = value;
  });

  children.flat().forEach((child) => {
    element.appendChild(createNode(child));
  });

  // console.log('[OUT]', element);

  return element;
}

// -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

console.log('Hello, world!');

// const element = (
//   <p>Hello, JSX!</p>
// );

function render(state) {
  const { count, number } = state;

  function handleClick(event) {
    console.log('<handleClick>', event);

    render({
      ...state,
      count: count + 1,
    });
  }

  function handleClickNumber(value) {
    render({
      ...state,
      number: number * 10 + value,
    });
  }

  const element = (
    <div id="greeting" className="row">
      <p>
        Hello, JSX!
      </p>
      <button type="button" onClick={handleClick}>
        Click me! (
        {count}
        )
      </button>
      <hr />
      <p>
        {number || 0}
      </p>
      {[1, 2, 3, 4, 5, 6, 7, 8, 9, 0].map((i) => (
        <button type="button" onClick={() => handleClickNumber(i)}>
          {i}
        </button>
      ))}
    </div>
  );

  // console.log('[RESULT]', element);

  const target = document.getElementById('app');
  target.textContent = '';
  target.appendChild(element);
}

render({
  count: 0,
  number: 0,
});
