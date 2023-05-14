import { useEffect } from 'react';

import styled from 'styled-components';

import ComboBox from '../ui/ComboBox';
import TextBox from '../ui/TextBox';
import CheckBox from '../ui/CheckBox';
import Button from '../ui/Button';

import Images from './Images';
import Options from './Options';

import useProductFormStore from '../../hooks/useProductFormStore';

import { Category } from '../../types';

const Container = styled.div`
  h2 {
    margin-bottom: 2rem;
    font-size: 2rem;
  }
`;

type ProductEditFormProps = {
  categories: Category[];
  onComplete: () => void;
}

export default function ProductEditForm({
  categories, onComplete,
}: ProductEditFormProps) {
  const [{
    category, images, name, price, options, description, hidden,
    valid, error, done,
  }, store] = useProductFormStore();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await store.update();
  };

  useEffect(() => {
    if (done) {
      onComplete();
    }
  }, [done]);

  return (
    <Container>
      <h2>Edit Product</h2>
      <form onSubmit={handleSubmit}>
        <ComboBox
          label="카테고리"
          selectedItem={category}
          items={categories}
          itemToId={(item) => item?.id || ''}
          itemToText={(item) => item?.name || ''}
          onChange={(value) => value && store.changeCategory(value)}
        />
        <Images images={images} store={store} />
        <TextBox
          label="상품명"
          value={name}
          onChange={(value) => store.changeName(value)}
        />
        <TextBox
          type="number"
          label="가격"
          value={price.toString()}
          onChange={(value) => store.changePrice(value)}
        />
        <Options options={options} store={store} />
        <TextBox
          label="설명"
          value={description}
          onChange={(value) => store.changeDescription(value)}
          multiline
        />
        <CheckBox
          label="감추기"
          checked={hidden}
          onChange={() => store.toggleHidden()}
        />
        <Button type="submit" disabled={!valid} leftPad>
          변경
        </Button>
        {error && (
          <p>상품 수정 실패</p>
        )}
      </form>
    </Container>
  );
}
