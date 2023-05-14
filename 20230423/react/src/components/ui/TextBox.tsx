import React, { useRef } from 'react';

import styled from 'styled-components';

const Container = styled.div`
  margin-block: .5rem;

  label,
  input,
  textarea {
    vertical-align: middle;
  }

  label {
    display: inline-block;
    width: ${(props) => props.theme.sizes.labelWidth};
    padding-right: .5rem;
    text-align: right;
  }

  input {
    width: 20rem;
  }

  input[type=number]::-webkit-inner-spin-button,
  input[type=number]::-webkit-outer-spin-button {
    -webkit-appearance: none;
  }

  textarea {
    width: 40rem;
    height: 10rem;
  }
`;

type TextBoxProps = {
  label: string;
  placeholder?: string;
  type?: 'text' | 'number' | 'password' | 'tel'; // ...and more types...
  value: string;
  onChange?: (value: string) => void;
  readOnly?: boolean;
  multiline?: boolean;
}

export default function TextBox({
  label, placeholder = undefined, type = 'text', value,
  onChange = undefined, readOnly = false, multiline = false,
}: TextBoxProps) {
  const id = useRef(`textbox-${Math.random().toString().slice(2)}`);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    if (!onChange) {
      return;
    }
    onChange(event.target.value);
  };

  return (
    <Container>
      <label htmlFor={id.current}>
        {label}
      </label>
      {React.createElement(multiline ? 'textarea' : 'input', {
        id: id.current,
        type,
        placeholder,
        value,
        onChange: handleChange,
        readOnly,
      })}
    </Container>
  );
}
