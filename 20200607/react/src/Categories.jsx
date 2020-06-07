import React from 'react';

export default function Categories({
  categories, category: selectedCategory, onClick,
}) {
  return (
    <ul>
      {categories.map((category) => (
        <li key={category.id}>
          <button
            type="button"
            onClick={() => onClick(category)}
          >
            {category.name}
          </button>
          {selectedCategory && category.id === selectedCategory.id ? '(V)' : ''}
        </li>
      ))}
    </ul>
  );
}
