import React from 'react';

import { List, Item } from '../styles/list-components';

function Categories({ categories, selectedCategory, onClick }) {
  return (
    <List>
      {categories.map((category) => (
        <Item
          key={category.id}
          active={selectedCategory && category.id === selectedCategory.id}
        >
          <button
            type="button"
            onClick={() => onClick(category.id)}
          >
            {category.name}
            {selectedCategory ? (
              <>
                {category.id === selectedCategory.id ? '(V)' : null}
              </>
            ) : null}
          </button>
        </Item>
      ))}
    </List>
  );
}

export default React.memo(Categories);
