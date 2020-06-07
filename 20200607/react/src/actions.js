import {
  fetchCategories,
  fetchRegions,
  fetchRestaurants,
} from './services/api';

export function setRestaurants(restaurants) {
  return {
    type: 'setRestaurants',
    payload: {
      restaurants,
    },
  };
}

export function changeRestaurantField({ name, value }) {
  return {
    type: 'changeRestaurantField',
    payload: {
      name,
      value,
    },
  };
}

export function addRestaurant() {
  return {
    type: 'addRestaurant',
  };
}

export function loadRestaurants({ category, region }) {
  return async (dispatch) => {
    if (!category || !region) {
      return;
    }

    const restaurants = await fetchRestaurants({
      categoryId: category.id,
      region: region.name,
    });
    dispatch(setRestaurants(restaurants));
  };
}

export function setCategories(categories) {
  return {
    type: 'setCategories',
    payload: {
      categories,
    },
  };
}

export function selectCategory(category) {
  return {
    type: 'selectCategory',
    payload: {
      category,
    },
  };
}

export function loadCategories() {
  return async (dispatch) => {
    const categories = await fetchCategories();
    dispatch(setCategories(categories));
  };
}

export function setRegions(regions) {
  return {
    type: 'setRegions',
    payload: {
      regions,
    },
  };
}

export function selectRegion(region) {
  return {
    type: 'selectRegion',
    payload: {
      region,
    },
  };
}

export function loadRegions() {
  return async (dispatch) => {
    const regions = await fetchRegions();
    dispatch(setRegions(regions));
  };
}
