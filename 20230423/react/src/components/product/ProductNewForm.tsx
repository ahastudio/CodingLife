import { useEffect } from 'react';

import styled from 'styled-components';

import ComboBox from '../ui/ComboBox';
import TextBox from '../ui/TextBox';
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

type ProductNewFormProps = {
  categories: Category[];
  onComplete: () => void;
}

export default function ProductNewForm({
  categories, onComplete,
}: ProductNewFormProps) {
  const [{
    category, images, name, price, options, description, valid, error, done,
  }, store] = useProductFormStore();

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    await store.create();
  };

  useEffect(() => {
    if (done) {
      onComplete();
    }
  }, [done]);

  return (
    <Container>
      <h2>New Product</h2>
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
        <Button type="submit" disabled={!valid} leftpad>
          등록
        </Button>
        {error && (
          <p>상품 등록 실패</p>
        )}
      </form>
    </Container>
  );
}
