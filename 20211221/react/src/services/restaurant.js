import { createApi, fetchBaseQuery } from '@reduxjs/toolkit/query/react';

const BASE_URL = 'https://eatgo-customer-api.ahastudio.com';

export const restaurantApi = createApi({
  reducerPath: 'restaurantApi',
  baseQuery: fetchBaseQuery({
    baseUrl: `${BASE_URL}/`,
  }),
  endpoints: (builder) => ({
    getRestaurant: builder.query({
      query: ({ region, category }) => (
        `restaurants?region=${region}&category=${category}`
      ),
    }),
  }),
});

export const { useGetRestaurantQuery } = restaurantApi;
