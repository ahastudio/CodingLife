/* eslint-disable react/react-in-jsx-scope, react/jsx-filename-extension */

/* @jsx createElement */

function createElement(tagName, props, ...children) {
  const element = document.createElement(tagName);

  Object.entries(props || {}).forEach(([key, value]) => {
    element[key.toLowerCase()] = value;
  });

  children.flat().forEach((child) => {
    if (child instanceof Node) {
      element.appendChild(child);
      return;
    }
    element.appendChild(document.createTextNode(child));
  });

  return element;
}

createElement();

//

const OPERATOR_FUNCTIONS = {
  '+': (x, y) => x + y,
  '-': (x, y) => x - y,
  '*': (x, y) => x * y,
  '/': (x, y) => x / y,
};

function calculate({ operator, result, current }) {
  if (!current) {
    return result;
  }
  if (!operator) {
    return current;
  }
  return OPERATOR_FUNCTIONS[operator](result, current);
}

function render(state) {
  const {
    count, current, operator, result,
  } = state;

  function handleClick() {
    render({
      ...state,
      count: count + 1,
    });
  }

  function handleClickNumber(value) {
    render({
      ...state,
      current: (current || 0) * 10 + value,
    });
  }

  function handleClickOperator(value) {
    render({
      ...state,
      current: null,
      operator: value,
      result: calculate({ operator, result, current }),
    });
  }

  const element = (
    <div id="hello" className="greeting">
      <p>Hello, world!</p>
      <p>
        <button type="button" onClick={() => handleClick()}>
          Click me!
          (
          {count}
          )
        </button>
      </p>
      <p>
        {current === null ? result : current}
      </p>
      <p>
        {[1, 2, 3, 4, 5, 6, 7, 8, 9, 0].map((i) => (
          <button type="button" onClick={() => handleClickNumber(i)}>
            {i}
          </button>
        ))}
      </p>
      <p>
        {['+', '-', '*', '/'].map((i) => (
          <button type="button" onClick={() => handleClickOperator(i)}>
            {i}
          </button>
        ))}
        <button type="button" onClick={() => handleClickOperator('')}>
          =
        </button>
      </p>
    </div>
  );

  document.getElementById('app').textContent = '';
  document.getElementById('app').appendChild(element);
}

render({
  count: 0,
  current: null,
  operator: null,
  result: 0,
});
