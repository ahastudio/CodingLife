import React, { useRef } from 'react';

import styled from 'styled-components';

const Container = styled.div`
  margin-block: .5rem;

  label,
  input {
    vertical-align: middle;
  }

  label {
    display: inline-block;
    width: ${(props) => props.theme.sizes.labelWidth};
    padding-right: .5rem;
    text-align: right;
  }
`;

type CheckBox = {
  label: string;
  checked: boolean;
  onChange: (checked: boolean) => void;
}

export default function CheckBox({
  label, checked, onChange,
}: CheckBox) {
  const id = useRef(`checkbox-${Math.random().toString().slice(2)}`);

  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    onChange(event.target.checked);
  };

  return (
    <Container>
      <label htmlFor={id.current}>
        {label}
      </label>
      <input
        id={id.current}
        type="checkbox"
        checked={checked}
        onChange={handleChange}
      />
    </Container>
  );
}
