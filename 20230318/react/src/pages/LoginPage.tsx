import { Link } from 'react-router-dom';

export default function LoginPage() {
  return (
    <div>
      <h2>Log In</h2>
      <Link to="/signup">회원 가입</Link>
    </div>
  );
}
