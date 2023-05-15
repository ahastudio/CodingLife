import ProductFormStore from './ProductFormStore';

import fixtures from '../../fixtures';

const createProduct = jest.fn();
const updateProduct = jest.fn();

jest.mock('../services/ApiService', () => ({
  get apiService() {
    return {
      createProduct,
      updateProduct,
    };
  },
}));

const context = describe;

describe('ProductFormStore', () => {
  let store: ProductFormStore;

  beforeEach(() => {
    jest.clearAllMocks();

    store = new ProductFormStore();
  });

  describe('toggleHidden', () => {
    it('changes “hidden” to true and false alternately', () => {
      expect(store.hidden).toBeFalsy();

      store.toggleHidden();

      expect(store.hidden).toBeTruthy();

      store.toggleHidden();

      expect(store.hidden).toBeFalsy();
    });
  });

  describe('create', () => {
    const [category] = fixtures.categories;

    beforeEach(() => {
      store.reset();

      expect(store.valid).toBeFalsy();

      store.changeCategory(category);
      store.changeName('New Product');
      store.changePrice('123400');
      store.changeDescription('What is this?');

      store.changeImageUrl(0, 'http://example.com/images/01.jpg');
      store.addImage();
      store.changeImageUrl(1, 'http://example.com/images/02.jpg');
      store.removeImage(1);

      expect(store.images).toHaveLength(1);

      store.addOption();
      store.addOption();
      store.addOption();
      store.removeOption(2);
      store.changeOptionName(0, 'Color');
      store.changeOptionName(1, 'Size');

      expect(store.options).toHaveLength(2);

      store.addOptionItem(0);
      store.addOptionItem(0);
      store.removeOptionItem(0, 2);
      store.changeOptionItemName(0, 0, 'Black');
      store.changeOptionItemName(0, 1, 'White');

      expect(store.options[0].items).toHaveLength(2);

      store.changeOptionItemName(1, 0, 'Free');

      expect(store.valid).toBeTruthy();

      store.toggleHidden();
    });

    context('when API responds with success', () => {
      it('sets done is true', async () => {
        await store.create();

        expect(createProduct).toBeCalled();

        expect(store.done).toBeTruthy();
        expect(store.error).toBeFalsy();
      });
    });

    context('when API responds with error', () => {
      beforeEach(() => {
        createProduct.mockRejectedValue(Error('Create Product API error!'));
      });

      it('sets error is true', async () => {
        await store.create();

        expect(createProduct).toBeCalled();

        expect(store.done).toBeFalsy();
        expect(store.error).toBeTruthy();
      });
    });
  });

  describe('update', () => {
    const [product] = fixtures.products;

    beforeEach(() => {
      store.setProduct(JSON.parse(JSON.stringify(product)));

      store.changeName('New Name');
    });

    context('when API responds with success', () => {
      it('sets done is true', async () => {
        await store.update();

        expect(updateProduct).toBeCalled();

        expect(store.done).toBeTruthy();
        expect(store.error).toBeFalsy();
      });
    });

    context('when API responds with error', () => {
      beforeEach(() => {
        updateProduct.mockRejectedValue(Error('Update Product API error!'));
      });

      it('sets error is true', async () => {
        await store.update();

        expect(updateProduct).toBeCalled();

        expect(store.done).toBeFalsy();
        expect(store.error).toBeTruthy();
      });
    });
  });
});
