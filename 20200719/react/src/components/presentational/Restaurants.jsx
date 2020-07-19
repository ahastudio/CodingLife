import React from 'react';

import styled from '@emotion/styled';

const List = styled.ul({
  margin: '1em 0',
  padding: 0,
  borderTop: '1px solid #DDDD',
  listStyle: 'none',
});

const Item = styled.li({
  borderBottom: '1px solid #DDDD',
  '& a': {
    display: 'block',
    fontSize: '1.4em',
    padding: '.5em',
    color: '#333',
    textDecoration: 'none',
    '&:hover': {
      color: '#000',
      background: '#EEE',
    },
  },
});

function Restaurants({ restaurants, onClick }) {
  const handleClick = (restaurant) => (event) => {
    event.preventDefault();
    onClick(restaurant);
  };

  return (
    <List>
      {restaurants.map((restaurant) => (
        <Item key={restaurant.id}>
          <a
            href={`/restaurants/${restaurant.id}`}
            onClick={handleClick(restaurant)}
          >
            {restaurant.name}
          </a>
        </Item>
      ))}
    </List>
  );
}

export default React.memo(Restaurants);
