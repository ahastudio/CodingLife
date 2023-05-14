import { useNavigate, useParams } from 'react-router-dom';

import { Controller, useForm } from 'react-hook-form';

import styled from 'styled-components';

import Button from '../components/ui/Button';

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

    img {
      width: 10rem;
    }
  }

  [type=submit] {
    display: block;
    margin-block: 1rem;
  }
`;

export default function OrderEditPage() {
  const params = useParams();

  const orderId = String(params.id);

  const navigate = useNavigate();

  const {
    order, loading, error, updateOrder,
  } = useFetchOrder({
    orderId,
  });

  type FormValues = {
    status: string;
  };

  const { handleSubmit, control } = useForm<FormValues>();

  const onSubmit = async (data: FormValues) => {
    await updateOrder({
      status: data.status,
    });
    navigate(`/orders/${orderId}`);
  };

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
      <h2>Order Status Transition</h2>
      <dl>
        <dt>주문일시</dt>
        <dd>{order.orderedAt}</dd>
        <dt>주문자</dt>
        <dd>{order.orderer.name}</dd>
        <dt>상품</dt>
        <dd>{order.title}</dd>
        <dt>총 가격</dt>
        <dd>
          {numberFormat(order.totalPrice)}
          원
        </dd>
      </dl>
      <form onSubmit={handleSubmit(onSubmit)}>
        <Controller
          control={control}
          name="status"
          defaultValue={order.status}
          render={({ field: { onChange, value } }) => (
            <div>
              <label htmlFor="input-status">상태</label>
              <select
                id="input-status"
                value={value}
                onChange={onChange}
              >
                {Object.keys(STATUS_MESSAGES).map((status) => (
                  <option key={status} value={status}>
                    {STATUS_MESSAGES[status]}
                  </option>
                ))}
              </select>
            </div>
          )}
        />
        <Button type="submit">
          변경
        </Button>
      </form>
    </Container>
  );
}
