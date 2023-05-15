import { Link } from 'react-router-dom';

import styled from 'styled-components';

import useFetchOrders from '../hooks/useFetchOrders';

import numberFormat from '../utils/numberFormat';

import { STATUS_MESSAGES } from '../constants';

const Container = styled.div`
  h2 {
    margin-bottom: 2rem;
    font-size: 2rem;
  }

  table {
    th, td {
      padding: 1rem;
      text-align: left;
    }

    th {
      border-bottom: .1rem solid ${(props) => props.theme.colors.secondary}
    }

    img {
      width: 10rem;
      vertical-align: middle;
    }

    a {
      display: block;
      margin-bottom: 1rem;
    }
  }

  > a {
    display: inline-block;
    margin-block: 1rem;
  }
`;

export default function OrderListPage() {
  const { orders, loading, error } = useFetchOrders();

  if (loading) {
    return (
      <p>Loading...</p>
    );
  }

  if (error) {
    return (
      <p>Error!</p>
    );
  }

  return (
    <Container>
      <h2>Orders</h2>
      <table>
        <thead>
          <tr>
            <th>주문일</th>
            <th>주문자</th>
            <th>상품</th>
            <th>총 가격</th>
            <th>상태</th>
            <th>행동</th>
          </tr>
        </thead>
        <tbody>
          {orders.map((order) => (
            <tr key={order.id}>
              <td>{order.orderedAt}</td>
              <td>{order.orderer.name}</td>
              <td>{order.title}</td>
              <td>
                {numberFormat(order.totalPrice)}
                원
              </td>
              <td>{STATUS_MESSAGES[order.status]}</td>
              <td>
                <Link to={`/orders/${order.id}`}>
                  자세히
                </Link>
                <Link to={`/orders/${order.id}/edit`}>
                  상태 변경
                </Link>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </Container>
  );
}
