import React from 'react';

import styled from '@emotion/styled';

const Container = styled.div({
  margin: '.8em 0',
});

const Label = styled.label({
  display: 'block',
  marginBottom: '.2em',
});

const Input = styled.input({
  display: 'block',
  padding: '0 1em',
  width: '100%',
  height: '3em',
});

export default function TextField({
  label, type = 'text', name, value, onChange,
}) {
  const id = `input-${name}`;

  function handleChange(event) {
    const { target } = event;
    onChange({ name, value: target.value });
  }

  return (
    <Container>
      <Label htmlFor={id}>
        {label}
      </Label>
      <Input
        type={type}
        id={id}
        name={name}
        value={value}
        onChange={handleChange}
      />
    </Container>
  );
}
