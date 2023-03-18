import { Link } from 'react-router-dom';

export default function OrderCompletePage() {
  return (
    <div>
      <h2>주문 완료</h2>
      <Link to="/orders">주문 목록</Link>
    </div>
  );
}
