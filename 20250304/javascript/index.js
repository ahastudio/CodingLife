function generateHTML(tag, props, elements) {
  const keys = Object.keys(props);
  const propsString = keys.length
    ? ' ' + keys.map((key) => `${key}="${props[key]}"`).join(' ')
    : '';
  return `<${tag}${propsString}>${elements.join('')}</${tag}>`;
}

function createElement(tag, props, elements) {
  const element = document.createElement(tag);
  Object.assign(element, props);
  elements.forEach((child) => {
    element.append(child);
  });
  return element;
}

['html', 'body', 'div', 'p', 'a'].forEach((tag) => {
  globalThis[tag] = (props, elements) => generateHTML(tag, props, elements);
  // globalThis[tag] = (props, elements) => createElement(tag, props, elements);
});

console.log((
  html({}, [
    body({}, [
      div({ id: 'app' }, [
        p({}, ['Hello, World!']),
        a({ href: 'https://example.com/' }, ['Click me!']),
      ]),
    ]),
  ])
));
