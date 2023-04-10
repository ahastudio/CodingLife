import { Link, useNavigate } from 'react-router-dom';

import styled from 'styled-components';

import Button from './ui/Button';

import useAccessToken from '../hooks/useAccessToken';
import useFetchCategories from '../hooks/useFetchCategories';

import { apiService } from '../services/ApiService';

const Container = styled.header`
  margin-bottom: 2rem;

  h1 {
    font-size: 4rem;
  }

  nav {
    padding-block: 2rem;

    ul {
      display: flex;
    }

    li {
      margin-right: 2rem;
    }

    .active {
      color: ${(props) => props.theme.colors.primary};
    }
  }
`;

export default function Header() {
  const navigate = useNavigate();

  const { accessToken, setAccessToken } = useAccessToken();

  const { categories } = useFetchCategories();

  const handleClickLogout = async () => {
    await apiService.logout();
    setAccessToken('');
    navigate('/');
  };

  return (
    <Container>
      <h1>Shop</h1>
      <nav>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/products">Products</Link>
            {!!categories.length && (
              <ul>
                {categories.map((category) => (
                  <li key={category.id}>
                    <Link to={`/products?categoryId=${category.id}`}>
                      {category.name}
                    </Link>
                  </li>
                ))}
              </ul>
            )}
          </li>
          {accessToken ? (
            <>
              <li>
                <Link to="/orders">Orders</Link>
              </li>
              <li>
                <Link to="/cart">Cart</Link>
              </li>
              <li>
                <Button onClick={handleClickLogout}>
                  Logout
                </Button>
              </li>
            </>
          ) : (
            <li>
              <Link to="/login">Login</Link>
            </li>
          )}
        </ul>
      </nav>
    </Container>
  );
}
