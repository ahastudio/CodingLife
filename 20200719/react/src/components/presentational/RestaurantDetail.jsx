import React from 'react';

import styled from '@emotion/styled';

import MenuItems from './MenuItems';

const Title = styled.h2({
  fontSize: '2em',
  margin: 0,
  marginBottom: '.5em',
});

const Address = styled.p({
  margin: '1em 0',
  color: '#333',
});

function RestaurantDetail({ restaurant }) {
  const { name, address, menuItems } = restaurant;

  return (
    <div>
      <Title>
        {name}
      </Title>
      <Address>
        주소:
        {' '}
        {address}
      </Address>
      <h3>
        메뉴
      </h3>
      <MenuItems menuItems={menuItems} />
    </div>
  );
}

export default React.memo(RestaurantDetail);
