import ProductList from './components/ProductList';
import Cart from './components/Cart';

export default function App() {
  return (
    <div style={{ display: 'flex' }}>
      <ProductList />
      <Cart />
    </div>
  );
}
