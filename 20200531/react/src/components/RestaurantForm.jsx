import React from 'react';

export default function RestaurantForm({ restaurantForm, onChange, onSubmit }) {
  function handleChange(event) {
    const { target: { name, value } } = event;
    onChange(name, value);
  }

  return (
    <>
      <p>
        <label htmlFor="input-restaurant-name">
          가게 이름
        </label>
        <input
          id="input-restaurant-name"
          type="text"
          name="name"
          value={restaurantForm.name}
          onChange={handleChange}
        />
      </p>
      <p>
        <label htmlFor="input-restaurant-category">
          분류
        </label>
        <input
          id="input-restaurant-category"
          type="text"
          name="category"
          value={restaurantForm.category}
          onChange={handleChange}
        />
      </p>
      <p>
        <label htmlFor="input-restaurant-address">
          주소
        </label>
        <input
          id="input-restaurant-address"
          type="text"
          name="address"
          value={restaurantForm.address}
          onChange={handleChange}
        />
      </p>
      <button
        type="button"
        onClick={onSubmit}
      >
        등록
      </button>
    </>
  );
}
