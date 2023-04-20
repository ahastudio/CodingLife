import { Link } from 'react-router-dom';

import Options from './Options';

import { LineItem } from '../../types';

import numberFormat from '../../utils/numberFormat';

type LineItemViewProps = {
  lineItem: LineItem;
};

export default function LineItemView({ lineItem }: LineItemViewProps) {
  return (
    <tr>
      <td>
        <Link to={`/products/${lineItem.product.id}`}>
          {lineItem.product.name}
        </Link>
        <Options options={lineItem.options} />
      </td>
      <td className="price">
        {numberFormat(lineItem.unitPrice)}
        원
      </td>
      <td>{lineItem.quantity}</td>
      <td className="price">
        {numberFormat(lineItem.totalPrice)}
        원
      </td>
    </tr>
  );
}
