import { Link } from 'react-router-dom';

export default function Header() {
  return (
    <header>
      <h2>Shop</h2>
      <nav>
        <ul>
          <li>
            <Link to="/">홈</Link>
          </li>
          <li>
            <Link to="/products">상품 목록</Link>
          </li>
          <li>
            <Link to="/orders">주문 목록</Link>
          </li>
          <li>
            <Link to="/cart">장바구니</Link>
          </li>
          <li>
            <Link to="/login">로그인</Link>
          </li>
        </ul>
      </nav>
      <hr />
    </header>
  );
}
