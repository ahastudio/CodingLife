import styled from 'styled-components';

import Table from '../line-item/Table';

import { OrderDetail } from '../../types';

const Container = styled.div`
  dl {
    display: flex;
    flex-wrap: wrap;
    line-height: 1.7;

    dt {
      width: 8rem;
    }

    dd {
      width: calc(100% - 8rem);
    }
  }
`;

type OrderProps = {
  order: OrderDetail;
};

export default function Order({ order }: OrderProps) {
  if (!order.lineItems.length) {
    return (
      <p>장바구니가 비었습니다</p>
    );
  }

  return (
    <Container>
      <dl>
        <dt>주문 일시</dt>
        <dd>{order.orderedAt}</dd>
        <dt>주문 코드</dt>
        <dd>{order.id}</dd>
      </dl>
      <Table
        lineItems={order.lineItems}
        totalPrice={order.totalPrice}
      />
    </Container>
  );
}
