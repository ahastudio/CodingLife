import { useNavigate } from 'react-router-dom';

import Table from '../line-item/Table';

import { Cart } from '../../types';
import Button from '../ui/Button';

type CartViewProps = {
  cart: Cart;
};

export default function CartView({ cart }: CartViewProps) {
  const navigate = useNavigate();

  if (!cart.lineItems.length) {
    return (
      <p>장바구니가 비었습니다</p>
    );
  }

  const handleClick = () => {
    navigate('/order');
  };

  return (
    <div>
      <Table
        lineItems={cart.lineItems}
        totalPrice={cart.totalPrice}
      />
      <Button onClick={handleClick}>
        주문하기
      </Button>
    </div>
  );
}
