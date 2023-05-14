import { Link } from 'react-router-dom';

import styled from 'styled-components';

import useFetchProducts from '../hooks/useFetchProducts';

import numberFormat from '../utils/numberFormat';

const Container = styled.div`
  h2 {
    margin-bottom: 2rem;
    font-size: 2rem;
  }

  table {
    th, td {
      padding: 1rem;
      text-align: left;
    }

    th {
      border-bottom: .1rem solid ${(props) => props.theme.colors.secondary}
    }

    img {
      width: 10rem;
      vertical-align: middle;
    }

    a {
      margin-right: 1rem;
    }
  }

  > a {
    display: inline-block;
    margin-block: 1rem;
  }
`;

export default function ProductListPage() {
  const { products, loading, error } = useFetchProducts();

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
      <h2>Products</h2>
      <table>
        <thead>
          <tr>
            <th>카테고리</th>
            <th>이미지</th>
            <th>제품명</th>
            <th>가격</th>
            <th>표시</th>
            <th>행동</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td>
                {product.category.name}
              </td>
              <td>
                {product.thumbnail?.url && (
                  <img src={product.thumbnail.url} alt="썸네일" />
                )}
              </td>
              <td>
                {product.name}
              </td>
              <td>
                {numberFormat(product.price)}
                원
              </td>
              <td>
                {product.hidden ? '숨김' : '보임'}
              </td>
              <td>
                <Link to={`/products/${product.id}`}>
                  자세히
                </Link>
                <Link to={`/products/${product.id}/edit`}>
                  수정
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <Link to="/products/new">
        상품 추가
      </Link>
    </Container>
  );
}
