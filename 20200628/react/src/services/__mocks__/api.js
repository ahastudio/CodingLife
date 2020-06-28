export async function fetchRegions() {
  return [];
}

export async function fetchCategories() {
  return [];
}

export async function fetchRestaurants() {
  return [];
}

export async function fetchRestaurant({ restaurantId }) {
  return {
    id: restaurantId,
    // TODO: ....
  };
}

export async function postLogin({ email, password }) {
  return {
    email,
    password,
    // TODO: ....
  };
}

export async function postReview({
  accessToken, restaurantId, score, description,
}) {
  return {
    accessToken,
    restaurantId,
    score,
    description,
    // TODO: ....
  };
}
