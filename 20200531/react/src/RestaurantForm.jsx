import React from 'react';

export default function RestaurantForm({ restaurantForm, onChange, onSubmit }) {
  function handleChangeName(e) {
    onChange('name', e.target.value);
  }

  function handleChangeCategory(e) {
    onChange('category', e.target.value);
  }

  function handleChangeAddress(e) {
    onChange('address', e.target.value);
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
          value={restaurantForm.name}
          onChange={handleChangeName}
        />
      </p>
      <p>
        <label htmlFor="input-restaurant-category">
          카테고리
        </label>
        <input
          id="input-restaurant-category"
          type="text"
          value={restaurantForm.category}
          onChange={handleChangeCategory}
        />
      </p>
      <p>
        <label htmlFor="input-restaurant-address">
          주소
        </label>
        <input
          id="input-restaurant-address"
          type="text"
          value={restaurantForm.address}
          onChange={handleChangeAddress}
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
