import React from 'react';

export default function Input({ value, onChange, onClick }) {
  return (
    <p>
      <input
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
