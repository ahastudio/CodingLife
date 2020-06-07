import React from 'react';

export default function RestaurantForm({ restaurant, onChange, onClick }) {
  function handleChange(event) {
    const { target: { name, value } } = event;
    onChange({ name, value });
  }

  return (
    <div>
      <input
        type="text"
        placeholder="이름"
        name="name"
        value={restaurant.name}
        onChange={handleChange}
      />
      <input
        type="text"
        placeholder="분류"
        name="category"
        value={restaurant.category}
        onChange={handleChange}
      />
      <input
        type="text"
        placeholder="주소"
        name="address"
        value={restaurant.address}
        onChange={handleChange}
      />
      <button
        type="button"
        onClick={onClick}
      >
        등록
      </button>
    </div>
  );
}
