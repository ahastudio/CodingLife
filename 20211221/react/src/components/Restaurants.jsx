import { useGetRestaurantQuery } from '../services/restaurant';

export default function Restaurants() {
  const { data: restaurants, error, isLoading } = useGetRestaurantQuery({
    region: '서울',
    category: 1,
  });

  if (error) {
    return (
      <p>Oh no, there was an error</p>
    );
  }

  if (isLoading) {
    return (
      <p>Loading...</p>
    );
  }

  if (!restaurants.length) {
    return (
      <p>No restaurant</p>
    );
  }

  return (
    <ul>
      {restaurants.map((restaurant) => (
        <li key={restaurant.id}>
          {restaurant.name}
          {' '}
          |
          {' '}
          {restaurant.address}
        </li>
      ))}
    </ul>
  );
}
