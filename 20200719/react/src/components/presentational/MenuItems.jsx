import React from 'react';

import styled from '@emotion/styled';

const List = styled.ul({
  margin: 0,
  padding: 0,
  borderTop: '1px solid #DDDD',
  listStyle: 'none',
});

const Item = styled.li({
  padding: '.4em',
  borderBottom: '1px solid #DDDD',
});

export default function MenuItems({ menuItems }) {
  if (!(menuItems || []).length) {
    return (
      <p>메뉴가 없어요!</p>
    );
  }

  return (
    <List>
      {menuItems.map((menuItem) => (
        <Item key={menuItem.id}>
          {menuItem.name}
        </Item>
      ))}
    </List>
  );
}
