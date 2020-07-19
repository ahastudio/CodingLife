import React from 'react';

import styled from '@emotion/styled';

const Container = styled.div({
  margin: '.8em 0',
});

const Label = styled.label({
  display: 'block',
  marginBottom: '.2em',
});

const Select = styled.select({
  display: 'block',
  padding: '0 1em',
  width: '100%',
  height: '3em',
});

export default function StarsField({
  label, name, value, onChange,
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
      <Select
        id={id}
        name={name}
        value={value}
        onChange={handleChange}
      >
        <option>
          평점을 선택하세요
        </option>
        {[1, 2, 3, 4, 5].map((i) => (
          <option key={i} value={i}>
            {'★'.repeat(i)}
            {'☆'.repeat(5 - i)}
          </option>
        ))}
      </Select>
    </Container>
  );
}
