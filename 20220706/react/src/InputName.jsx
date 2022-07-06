import styled from 'styled-components';

const Label = styled.label`
  font-weight: bold;
  margin-right: .4em;
`;

export default function InputName({ name, onChange }) {
  return (
    <p>
      <Label htmlFor="input-name">
        Name
      </Label>
      <input
        id="input-name"
        type="text"
        value={name}
        onChange={onChange}
      />
    </p>
  );
}
