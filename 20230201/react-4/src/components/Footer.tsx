import { useNavigate } from 'react-router-dom';

export default function Footer() {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate('/about');
  };

  return (
    <footer>
      <hr />
      Footer
      <button type="button" onClick={handleClick}>
        Press
      </button>
    </footer>
  );
}
