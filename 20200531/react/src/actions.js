export function addRestaurant() {
  return {
    type: 'addRestaurant',
  };
}

export function changeRestaurantForm(fieldName, value) {
  return {
    type: 'changeRestaurantForm',
    payload: {
      fieldName,
      value,
    },
  };
}
