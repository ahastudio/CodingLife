const initialRestaurant = {
  name: '',
  category: '',
  address: '',
};

const initialState = {
  newId: 100,
  restaurants: [],
  restaurant: initialRestaurant,
  categories: [],
  category: null,
  regions: [],
  region: null,
};

function defaultReducer(state) {
  return state;
}

const reducers = {
  setRestaurants(state, { restaurants }) {
    return {
      ...state,
      restaurants,
    };
  },

  changeRestaurantField(state, { name, value }) {
    return {
      ...state,
      restaurant: {
        ...state.restaurant,
        [name]: value,
      },
    };
  },

  addRestaurant(state) {
    const { newId, restaurants, restaurant } = state;

    return {
      ...state,
      newId: newId + 1,
      restaurants: [...restaurants, { ...restaurant, id: newId }],
      restaurant: initialRestaurant,
    };
  },

  setCategories(state, { categories }) {
    return {
      ...state,
      categories,
    };
  },

  selectCategory(state, { category }) {
    return {
      ...state,
      category,
    };
  },

  setRegions(state, { regions }) {
    return {
      ...state,
      regions,
    };
  },

  selectRegion(state, { region }) {
    return {
      ...state,
      region,
    };
  },
};

export default function reducer(state = initialState, action) {
  return (reducers[action.type] || defaultReducer)(state, action.payload);
}
