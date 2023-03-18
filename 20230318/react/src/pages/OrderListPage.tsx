import { Link } from 'react-router-dom';

export default function OrderListPage() {
  return (
    <div>
      <h2>Order List</h2>
      <ul>
        <li>
          <Link to="/orders/1">Order #1</Link>
        </li>
        <li>
          <Link to="/orders/2">Order #2</Link>
        </li>
      </ul>
    </div>
  );
}
