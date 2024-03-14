import TextBox from '../ui/TextBox';
import Button from '../ui/Button';

import OptionItems from './OptionItems';

import ProductFormStore from '../../stores/ProductFormStore';

import { ProductOption } from '../../types';

import { key } from '../../utils';

type OptionsProps = {
  options: ProductOption[];
  store: Readonly<ProductFormStore>;
}

export default function Options({ options, store }: OptionsProps) {
  return (
    <ul>
      {options.map((option, index) => (
        <li key={key(option.id ?? '', index)}>
          <TextBox
            label={`옵션 #${index + 1}`}
            value={option.name}
            onChange={(value) => store.changeOptionName(index, value)}
          />
          <Button onClick={() => store.removeOption(index)} leftpad>
            옵션 삭제
          </Button>
          <OptionItems
            optionIndex={index}
            items={option.items}
            store={store}
          />
        </li>
      ))}
      <li>
        <Button onClick={() => store.addOption()} leftpad>
          옵션 추가
        </Button>
      </li>
    </ul>
  );
}
