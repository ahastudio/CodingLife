import Orders from '../components/order-list/Orders';

import useFetchOrders from '../hooks/useFetchOrders';

export default function OrderListPage() {
  const { orders } = useFetchOrders();

  return (
    <div>
      <h2>주문 목록</h2>
      <Orders orders={orders} />
    </div>
  );
}
