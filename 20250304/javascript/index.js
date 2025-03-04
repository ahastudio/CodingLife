function generateHTML(tag, props, elements) {
  const keys = Object.keys(props);
  const propsString = keys.length
    ? ' ' + keys.map((key) => `${key}="${props[key]}"`).join(' ')
    : '';
  return `<${tag}${propsString}>${elements.join('')}</${tag}>`;
}

['html', 'body', 'div', 'p', 'a'].forEach((tag) => {
  globalThis[tag] = (props, elements) => generateHTML(tag, props, elements);
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
