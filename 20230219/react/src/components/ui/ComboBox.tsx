import React, { useRef } from 'react';

import styled from 'styled-components';

const Container = styled.div`
  label {
    margin-right: .5rem;
  }
`;

type ComboBoxProps<T> = {
  label: string;
  selectedItem: T;
  items: T[];
  itemToId: (item: T) => string;
  itemToText: (item: T) => string;
  onChange: (item: T | null) => void;
}

export default function ComboBox<T>({
  label, selectedItem, items, itemToId, itemToText, onChange,
}: ComboBoxProps<T>) {
  const id = useRef(`combobox-${Math.random().toString().slice(2)}`);

  const handleChange = (event: React.ChangeEvent<HTMLSelectElement>) => {
    const { value } = event.target;
    const selected = items.find((item) => itemToId(item) === value);
    onChange(selected ?? null);
  };

  return (
    <Container>
      <label htmlFor={id.current}>
        {label}
      </label>
      <select
        id={id.current}
        value={itemToId(selectedItem)}
        onChange={handleChange}
      >
        {items.map((item) => (
          <option key={itemToId(item)} value={itemToId(item)}>
            {itemToText(item)}
          </option>
        ))}
      </select>
    </Container>
  );
}
