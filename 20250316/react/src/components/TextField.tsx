import { useRef } from "react";

export default function TextField({ label, name, placeholder }: {
  label: string;
  name: string;
  placeholder: string;
}) {
  const idRef = useRef(`input-${Math.random().toString(16).slice(2)}`);

  return (
    <div>
      <label htmlFor={idRef.current}>{label}</label>
      <input
        type="text"
        name={name}
        placeholder={placeholder}
        id={idRef.current}
      />
    </div>
  );
}
