import styled from 'styled-components';

import Button from '../../ui/Button';

import useProductFormStore from '../../../hooks/useProductFormStore';

const Container = styled.div`
  input {
    width: 5rem;
    text-align: center;
  }
`;

export default function Quantity() {
  const [{ quantity }, store] = useProductFormStore();

  const handleClickDecrease = () => {
    store.changeQuantity(quantity - 1);
  };

  const handleClickIncrease = () => {
    store.changeQuantity(quantity + 1);
  };

  return (
    <Container>
      <Button onClick={handleClickDecrease}>
        -
      </Button>
      <input type="text" value={quantity} readOnly />
      <Button onClick={handleClickIncrease}>
        +
      </Button>
    </Container>
  );
}
