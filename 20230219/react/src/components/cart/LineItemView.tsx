import { Link } from 'react-router-dom';

import styled from 'styled-components';

import Options from './Options';

import { LineItem } from '../../types';

import numberFormat from '../../utils/numberFormat';

const ProductLink = styled(Link)`
  font-weight: bold;
  text-decoration: none;
`;

type LineItemViewProps = {
  lineItem: LineItem;
};

export default function LineItemView({ lineItem }: LineItemViewProps) {
  return (
    <tr>
      <td>
        <ProductLink to={`/products/${lineItem.product.id}`}>
          {lineItem.product.name}
        </ProductLink>
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
