import { Link } from 'react-router-dom';

export default function OrderPage() {
  return (
    <div>
      <h2>Order</h2>
      <Link to="/order/complete">결제</Link>
    </div>
  );
}
