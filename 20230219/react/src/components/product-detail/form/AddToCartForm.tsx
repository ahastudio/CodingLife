import styled from 'styled-components';

import Options from './Options';
import Quantity from './Quantity';
import Price from './Price';
import SubmitButton from './SubmitButton';

const Container = styled.div`
  margin-bottom: 2rem;
  line-height: 1.8;
`;

export default function AddToCartForm() {
  return (
    <Container>
      <Options />
      <Quantity />
      <Price />
      <SubmitButton />
    </Container>
  );
}
