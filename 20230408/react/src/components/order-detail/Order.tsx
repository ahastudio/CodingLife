import styled from 'styled-components';

import Table from '../line-item/Table';

import { OrderDetail } from '../../types';

const STATUS_MESSAGES: Record<string, string> = {
  paid: '결제 완료',
};

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
    return null;
  }

  return (
    <Container>
      <dl>
        <dt>주문 일시</dt>
        <dd>{order.orderedAt}</dd>
        <dt>주문 코드</dt>
        <dd>{order.id}</dd>
        <dt>처리 상태</dt>
        <dd>{STATUS_MESSAGES[order.status]}</dd>
      </dl>
      <Table
        lineItems={order.lineItems}
        totalPrice={order.totalPrice}
      />
    </Container>
  );
}
