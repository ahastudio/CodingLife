import React from 'react';

type GreetingProps = {
  name: string;
}

export default function Greeting({ name }: GreetingProps) {
  return (
    React.createElement(
      'p',
      {},
      'Hello, ',
      name,
      '!',
    )
  );
}
