/** @jsx createElement */

const $ = (selectors) => document.querySelector(selectors);

const createElement = (type, props, ...children) => {
  const attached = props ? Object.keys(props).map(key => {
    if (key === 'onClick') {
      console.log(props[key]);
      return `onClick="(${props[key]})();return false;"`;
    }
    return `${key}="${props[key]}"`;
  }) : [];
  return `<${[type, ...attached].join(' ' )}>${children.join('')}</${type}>`;
};

const handleClick = () => {
  alert('Hello!');
};

const element = (
  <div id="header">
    <h1>JSX</h1>
    <p>Welcome!</p>
    <hr />
    <button onClick={handleClick}>Click!</button>
  </div>
);

console.log(element);

$('#app').innerHTML = element;
