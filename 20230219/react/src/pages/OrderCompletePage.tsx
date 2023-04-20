import { Link } from 'react-router-dom';

export default function OrderCompletePage() {
  return (
    <div>
      <p>
        주문이 완료되었습니다.
      </p>
      <p>
        <Link to="/orders">주문 목록 확인</Link>
      </p>
    </div>
  );
}
