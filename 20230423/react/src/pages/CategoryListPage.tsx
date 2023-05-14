import { Link } from 'react-router-dom';

import styled from 'styled-components';

import useFetchCategories from '../hooks/useFetchCategories';

const Container = styled.div`
  h2 {
    margin-bottom: 2rem;
    font-size: 2rem;
  }

  table {
    th, td {
      padding: .5rem;
    }
  }

  > a {
    display: inline-block;
    margin-block: 1rem;
  }
`;

export default function CategoryListPage() {
  const { categories, loading, error } = useFetchCategories();

  if (loading) {
    return (
      <p>Loading...</p>
    );
  }

  if (error) {
    return (
      <p>Error!</p>
    );
  }

  return (
    <Container>
      <h2>Categories</h2>
      <table>
        <thead>
          <tr>
            <th>이름</th>
            <th>표시</th>
            <th>행동</th>
          </tr>
        </thead>
        <tbody>
          {categories.map((category) => (
            <tr key={category.id}>
              <td>{category.name}</td>
              <td>{category.hidden ? '감춤' : '보임'}</td>
              <td>
                <Link to={`/categories/${category.id}/edit`}>수정</Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/categories/new">
        카테고리 추가
      </Link>
    </Container>
  );
}
