import styled from 'styled-components';

import ShippingForm from './ShippingForm';
import PaymentButton from './PaymentButton';

import Table from '../line-item/Table';

import { Cart } from '../../types';

const Container = styled.div`
  h2 {
    font-size: 4rem;
  }
`;

type OrderFormProps = {
  cart: Cart;
};

export default function OrderForm({ cart }: OrderFormProps) {
  return (
    <Container>
      <h2>주문</h2>
      <Table
        lineItems={cart.lineItems}
        totalPrice={cart.totalPrice}
      />
      <ShippingForm />
      <PaymentButton cart={cart} />
    </Container>
  );
}
