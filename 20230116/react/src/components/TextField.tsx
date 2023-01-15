import React from 'react';

type TextFiledProps = {
  placeholder: string;
  filterText: string;
  setFilterText: (value: string) => void;
};

export default function TextField({
  placeholder, filterText, setFilterText,
}: TextFiledProps) {
  const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setFilterText(value);
  };

  return (
    <div>
      <input
        type="text"
        placeholder={placeholder}
        value={filterText}
        onChange={handleChange}
      />
    </div>
  );
}
