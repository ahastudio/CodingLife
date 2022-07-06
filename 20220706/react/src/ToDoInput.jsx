import { useState } from 'react';

import styled from 'styled-components';

const Label = styled.label`
  font-weight: bold;
  margin-right: .4em;
`;

const TextField = styled.input`
  font-size: 1em;
  padding: .2em .4em;
  border: 1px solid #CCC;
  border-radius: 4px;
`;

const Button = styled.button`
  font-size: 1em;
  margin-left: .2em;
  padding: .2em .4em;
  border: 1px solid #888;
  border-radius: 4px;
  background: #FFF;
`;

export default function ToDoInput({ addTask }) {
  const [title, setTitle] = useState('');

  const handleChange = (event) => {
    setTitle(event.target.value);
  };

  const handleClick = () => {
    addTask(title);
    setTitle('');
  };

  return (
    <p>
      <Label htmlFor="input-task-title">
        Task
      </Label>
      <TextField
        id="input-task-title"
        type="text"
        value={title}
        onChange={handleChange}
      />
      <Button type="button" onClick={handleClick}>
        추가
      </Button>
    </p>
  );
}
