import React from 'react';

import { render } from '@testing-library/react';

import MenuItems from './MenuItems';

describe('MenuItems', () => {
  context('with menu items', () => {
    it('renders menu items', () => {
      const menuItems = [
        { id: 1, name: '공기밥' },
      ];

      const { container } = render(<MenuItems menuItems={menuItems} />);

      expect(container).toHaveTextContent('공기밥');
    });
  });

  context('without menu item', () => {
    it('renders no items message', () => {
      [[], null, undefined].forEach((menuItems) => {
        const { container } = render(<MenuItems menuItems={menuItems} />);

        expect(container).toHaveTextContent('메뉴가 없어요');
      });
    });
  });
});
