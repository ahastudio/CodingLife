import { Link } from 'react-router-dom';

export default function CartPage() {
  return (
    <div>
      <h2>Cart</h2>
      <Link to="/order">주문하기</Link>
    </div>
  );
}
