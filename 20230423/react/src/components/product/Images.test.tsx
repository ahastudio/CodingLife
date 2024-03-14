import { container } from 'tsyringe';

import { screen, fireEvent } from '@testing-library/react';

import { render } from '../../test-helpers';

import Images from './Images';

import ProductFormStore from '../../stores/ProductFormStore';

const context = describe;

describe('Images', () => {
  let store: ProductFormStore;

  beforeEach(async () => {
    container.reset();

    store = container.resolve(ProductFormStore);
    store.reset();
  });

  function renderImages() {
    render((
      <Images
        images={store.images}
        store={store}
      />
    ));
  }

  it('renders image fields', () => {
    renderImages();

    screen.getByLabelText(/이미지/);
  });

  context('when the image URL is changed', () => {
    it('changes URL', async () => {
      const imageUrl = 'http://example.com/product.jpg';

      renderImages();

      fireEvent.change(screen.getByLabelText(/이미지/), {
        target: { value: imageUrl },
      });

      expect(store.images[0].url).toBe(imageUrl);
    });
  });

  context('when “Add” button is clicked', () => {
    it('append a new image', async () => {
      renderImages();

      expect(store.images).toHaveLength(1);

      fireEvent.click(screen.getByText('이미지 추가'));

      expect(store.images).toHaveLength(2);
    });
  });

  context('when “Delete” button is clicked', () => {
    it('append a new image', async () => {
      renderImages();

      expect(store.images).toHaveLength(1);

      fireEvent.click(screen.getByText('이미지 삭제'));

      expect(store.images).toHaveLength(0);
    });
  });
});
