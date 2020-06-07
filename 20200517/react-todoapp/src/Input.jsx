import React from 'react';

export default function Input({ value, onChange, onClick }) {
  return (
    <p>
      <label htmlFor="input-task-title">
        할 일
      </label>
      <input
        id="input-task-title"
        type="text"
        placeholder="할 일을 입력해 주세요"
        value={value}
        onChange={onChange}
      />
      <button type="button" onClick={onClick}>
        추가
      </button>
    </p>
  );
}
