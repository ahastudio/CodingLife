import { useParams } from 'react-router-dom';

export default function OrderDetailPage() {
  const { id } = useParams();

  return (
    <div>
      <h2>
        Order
        {' '}
        {id}
      </h2>
    </div>
  );
}
