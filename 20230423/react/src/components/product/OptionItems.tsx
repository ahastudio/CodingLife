import styled from 'styled-components';

import TextBox from '../ui/TextBox';
import Button from '../ui/Button';

import ProductFormStore from '../../stores/ProductFormStore';

import { ProductOptionItem } from '../../types';

import { key } from '../../utils';

const Container = styled.div`
  ol {
    margin-left: ${(props) => props.theme.sizes.labelWidth};

    label {
      display: none;
    }
  }
`;

type OptionItemsProps = {
  optionIndex: number;
  items: ProductOptionItem[];
  store: Readonly<ProductFormStore>;
}

export default function OptionItems({
  optionIndex, items, store,
}: OptionItemsProps) {
  return (
    <Container>
      <ol>
        {items.map((item, index) => (
          <li key={key(item.id ?? '', index)}>
            <TextBox
              label={`옵션 아이템 #${optionIndex + 1}-${index + 1}`}
              value={item.name}
              onChange={(value) => (
                store.changeOptionItemName(optionIndex, index, value)
              )}
            />
            <Button onClick={() => store.removeOptionItem(optionIndex, index)}>
              아이템 삭제
            </Button>
          </li>
        ))}
        <li>
          <Button onClick={() => store.addOptionItem(optionIndex)}>
            아이템 추가
          </Button>
        </li>
      </ol>
    </Container>
  );
}
