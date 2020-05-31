export const initialState = {
  newId: 1,
  restaurants: [],
  restaurantForm: {
    name: '',
    category: '',
    address: '',
  },
};

export default function reducer(state = initialState, action) {
  if (action.type === 'addRestaurant') {
    return {
      ...state,
      newId: state.newId + 1,
      restaurants: [
        ...state.restaurants,
        {
          id: state.newId,
          ...state.restaurantForm,
        },
      ],
    };
  }

  if (action.type === 'changeRestaurantForm') {
    const { payload: { fieldName, value } } = action;

    return {
      ...state,
      restaurantForm: {
        ...state.restaurantForm,
        [fieldName]: value,
      },
    };
  }

  return state;
}
