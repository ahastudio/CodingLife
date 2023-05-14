import { Link, useParams } from 'react-router-dom';

import styled from 'styled-components';

import Description from '../components/Description';

import useFetchProduct from '../hooks/useFetchProduct';

import numberFormat from '../utils/numberFormat';

const Container = styled.div`
  h2 {
    margin-bottom: 2rem;
    font-size: 2rem;
  }

  dl {
    &::after {
      clear: left;
      display: block;
      content: "";
    }

    dt {
      clear: left;
      width: 10rem;
      margin-right: 1rem;
      text-align: right;
    }

    dt, dd {
      float: left;
      margin-block: .5rem;
    }

    img {
      width: 10rem;
    }
  }

  > a {
    display: inline-block;
    margin-block: 1rem;
  }
`;

export default function ProductDetailPage() {
  const params = useParams();

  const { product, loading, error } = useFetchProduct({
    productId: String(params.id),
  });

  if (loading) {
    return (
      <p>Loading...</p>
    );
  }

  if (error || !product) {
    return (
      <p>Error!</p>
    );
  }

  return (
    <Container>
      <h2>Product Detail</h2>
      <dl>
        <dt>이름</dt>
        <dd>{product.name}</dd>
        <dt>카테고리</dt>
        <dd>{product.category.name}</dd>
        <dt>이미지</dt>
        <dd>
          {product.images.map((image) => (
            <img
              key={image.url}
              src={image.url}
              alt="썸네일"
            />
          ))}
        </dd>
        <dt>가격</dt>
        <dd>
          {numberFormat(product.price)}
          원
        </dd>
        <dt>옵션</dt>
        <dd>
          {product.options.map((option) => (
            <div key={option.id}>
              {option.name}
              :
              {' '}
              {option.items.map((item) => item.name).join(', ')}
            </div>
          ))}
        </dd>
        <dt>설명</dt>
        <dd>
          <Description value={product.description} />
        </dd>
        <dt>표시</dt>
        <dd>{product.hidden ? '숨김' : '보임'}</dd>
      </dl>
      <Link to={`/products/${product.id}/edit`}>
        수정
      </Link>
    </Container>
  );
}
