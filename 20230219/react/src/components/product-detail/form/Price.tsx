import styled from 'styled-components';

import useProductFormStore from '../../../hooks/useProductFormStore';

import numberFormat from '../../../utils/numberFormat';

const Container = styled.div`
  margin-block: .8rem;
  font-weight: bold;
`;

export default function Price() {
  const [{ price }] = useProductFormStore();

  return (
    <Container>
      {numberFormat(price)}
      원
    </Container>
  );
}
