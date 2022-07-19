import { memo } from 'react';

import Field from '../models/Field';

type TextInputProps = {
  name: string;
  type: string;
  field: Field;
  onChange: (value: string) => void;
}

function TextInput({
  name, type, field, onChange,
}: TextInputProps) {
  const { value, error } = field;

  return (
    <div>
      <label htmlFor={`input-${name}`}>
        Name
      </label>
      <input
        id={`input-${name}`}
        type={type}
        name={name}
        value={value}
        onChange={(e) => onChange(e.target.value)}
      />
      {error && (
        <div>
          {error}
        </div>
      )}
    </div>
  );
}

export default memo(TextInput);
