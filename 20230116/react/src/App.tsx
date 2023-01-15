import FilterableProductTable from './components/FilterableProductTable';

import Product from './types/Product';

const products: Product[] = [
  {
    category: 'Fruits', price: '$1', stocked: true, name: 'Apple',
  },
  {
    category: 'Fruits', price: '$1', stocked: true, name: 'Dragonfruit',
  },
  {
    category: 'Fruits', price: '$2', stocked: false, name: 'Passionfruit',
  },
  {
    category: 'Vegetables', price: '$2', stocked: true, name: 'Spinach',
  },
  {
    category: 'Vegetables', price: '$4', stocked: false, name: 'Pumpkin',
  },
  {
    category: 'Vegetables', price: '$1', stocked: true, name: 'Peas',
  },
];

export default function App() {
  return (
    <div>
      <h1>상품</h1>
      <FilterableProductTable products={products} />
    </div>
  );
}
