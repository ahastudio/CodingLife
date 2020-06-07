import React from 'react';

import { useSelector, useDispatch } from 'react-redux';

import Categories from './Categories';

import {
  selectCategory,
  loadRestaurants,
} from './actions';

export default function CategoriesContainer() {
  const dispatch = useDispatch();

  const {
    categories, selectedCategory, region,
  } = useSelector((state) => ({
    categories: state.categories,
    selectedCategory: state.category,
    region: state.region,
  }));

  function handleClick(category) {
    dispatch(selectCategory(category));
    dispatch(loadRestaurants({ category, region }));
  }

  return (
    <Categories
      categories={categories}
      category={selectedCategory}
      onClick={handleClick}
    />
  );
}
