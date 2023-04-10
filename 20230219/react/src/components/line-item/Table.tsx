import styled from 'styled-components';

import LineItemView from './LineItemView';

import { LineItem } from '../../types';

import numberFormat from '../../utils/numberFormat';

const Container = styled.div`
  table {
    margin-block: 1rem;
    width: 100%;
  }

  th, td {
    padding: .5rem;
    text-align: left;
  }
`;

type TableProps = {
  lineItems: LineItem[];
  totalPrice: number;
};

export default function Table({ lineItems, totalPrice }: TableProps) {
  if (!lineItems.length) {
    return null;
  }

  return (
    <Container>
      <table>
        <thead>
          <tr>
            <th>제품</th>
            <th>단가</th>
            <th>수량</th>
            <th>금액</th>
          </tr>
        </thead>
        <tbody>
          {lineItems.map((lineItem) => (
            <LineItemView
              key={lineItem.id}
              lineItem={lineItem}
            />
          ))}
        </tbody>
        <tfoot>
          <tr>
            <th colSpan={3}>
              합계
            </th>
            <td>
              {numberFormat(totalPrice)}
              원
            </td>
          </tr>
        </tfoot>
      </table>
    </Container>
  );
}
