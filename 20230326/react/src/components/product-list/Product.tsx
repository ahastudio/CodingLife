import styled from 'styled-components';

import { ProductSummary } from '../../types';

import numberFormat from '../../utils/numberFormat';

const Container = styled.div`
  div {
    margin-top: .5rem;
    word-break: keep-all;
  }
`;

const Thumbnail = styled.img.attrs({
  alt: 'Thumbnail',
})`
  display: block;
  width: 100%;
  aspect-ratio: 1/1;
`;

type ProductProps = {
  product: ProductSummary;
}

export default function Product({ product }: ProductProps) {
  return (
    <Container>
      <Thumbnail src={product.thumbnail.url} />
      <div>{product.name}</div>
      <div>
        {numberFormat(product.price)}
        원
      </div>
    </Container>
  );
}
