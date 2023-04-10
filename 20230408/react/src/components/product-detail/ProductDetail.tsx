import styled from 'styled-components';

import Images from './Images';
import Description from './Description';

import AddToCartForm from './form/AddToCartForm';

import useProductDetailStore from '../../hooks/useProductDetailStore';

const Container = styled.div`
  display: flex;
  justify-content: space-between;

  aside {
    width: 38%;
  }

  article {
    width: 60%;

    h2 {
      margin-bottom: 1rem;
      font-size: 2rem;
    }
  }
`;

export default function ProductDetailView() {
  const [{ product }] = useProductDetailStore();

  return (
    <Container>
      <aside>
        <Images images={product.images} />
      </aside>
      <article>
        <h2>{product.name}</h2>
        <AddToCartForm />
        <Description value={product.description} />
      </article>
    </Container>
  );
}
