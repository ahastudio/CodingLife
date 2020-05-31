import reducer from './reducer';

import {
  addRestaurant,
  changeRestaurantForm,
} from './actions';

import fixtures from './fixtures';

describe('reducer', () => {
  const newRestaurant = {
    name: '김밥제국',
    category: '분식',
    address: '서울특별시 강남구 역삼동',
  };

  describe('addRestaurant', () => {
    const initialState = {
      newId: 101,
      restaurants: fixtures.restaurants,
      restaurantForm: { ...newRestaurant },
    };

    it('appends a new restaurant into restaurants', () => {
      const oldCount = initialState.restaurants.length;

      const { newId, restaurants } = reducer(initialState, addRestaurant());

      expect(newId - initialState.newId).toBe(1);
      expect(restaurants.length - oldCount).toBe(1);

      const lastRestaurant = restaurants[restaurants.length - 1];

      expect(lastRestaurant.id).toEqual(101);
      expect(lastRestaurant.name).toEqual(newRestaurant.name);
    });
  });

  describe('changeRestaurantForm', () => {
    const initialState = {
      restaurantForm: { ...newRestaurant },
    };

    const state = reducer(initialState, changeRestaurantForm('name', '김밥왕국'));

    expect(state.restaurantForm.name).toBe('김밥왕국');
  });
});
