import { Link, useParams } from 'react-router-dom';

import styled from 'styled-components';

import Options from '../components/Options';

import useFetchOrder from '../hooks/useFetchOrder';

import numberFormat from '../utils/numberFormat';

import { STATUS_MESSAGES } from '../constants';

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

    span {
      margin-left: .5rem;
      font-size: 1.4rem;
    }
  }

  > a {
    display: inline-block;
    margin-block: 1rem;
  }
`;

export default function OrderEditPage() {
  const params = useParams();

  const { order, loading, error } = useFetchOrder({
    orderId: String(params.id),
  });

  if (loading) {
    return (
      <p>Loading...</p>
    );
  }

  if (error || !order) {
    return (
      <p>Error!</p>
    );
  }

  return (
    <Container>
      <h2>Order Detail</h2>
      <dl>
        <dt>주문일시</dt>
        <dd>{order.orderedAt}</dd>
        <dt>주문자</dt>
        <dd>{order.orderer.name}</dd>
        <dt>상품</dt>
        <dd>
          <ul>
            {order.lineItems.map((lineItem) => (
              <li key={lineItem.id}>
                {lineItem.product.name}
                <Options options={lineItem.options} />
              </li>
            ))}
          </ul>
        </dd>
        <dt>총 가격</dt>
        <dd>
          {numberFormat(order.totalPrice)}
          원
        </dd>
        <dt>배송 정보</dt>
        <dd>
          <p>
            받는 사람:
            {' '}
            {order.receiver.name}
          </p>
          <p>
            연락처:
            {' '}
            {order.receiver.phoneNumber}
          </p>
          <p>
            {order.receiver.address1}
            {' '}
            {order.receiver.address2}
            {' '}
            (우편번호:
            {' '}
            {order.receiver.postalCode}
            )
          </p>
        </dd>
        <dt>결제 정보</dt>
        <dd>
          <p>
            주문번호:
            {' '}
            {order.payment.merchantId}
          </p>
          <p>
            결제고유번호:
            {' '}
            {order.payment.transactionId}
          </p>
        </dd>
        <dt>상태</dt>
        <dd>{STATUS_MESSAGES[order.status]}</dd>
      </dl>
      <Link to={`/orders/${order.id}/edit`}>
        상태 변경
      </Link>
    </Container>
  );
}
