import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import CategoriesMenu from '../presentational/CategoriesMenu';

import {
  selectCategory,
  loadRestaurants,
} from '../actions';

import { get } from '../utils';

export default function CategoriesContainer() {
  const dispatch = useDispatch();

  const categories = useSelector(get('categories'));
  const selectedCategory = useSelector(get('selectedCategory'));

  function handleClick(category) {
    dispatch(selectCategory(category.id));
    dispatch(loadRestaurants());
  }

  return (
    <CategoriesMenu
      categories={categories}
      selectedCategory={selectedCategory}
      onClick={handleClick}
    />
  );
}
