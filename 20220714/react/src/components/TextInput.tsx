import Field from '../models/Field';

export default function TextInput({
  type, name, field, onChange,
}: {
  name: string;
  type: string;
  field: Field;
  onChange: (value: string) => void;
}) {
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
