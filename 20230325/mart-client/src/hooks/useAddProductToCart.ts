import { Product } from '../types';

const baseUrl = process.env.API_BASE_URL || 'http://localhost:3000';

export default function useAddProductToCart() {
  const addProductToCart = async (product: Product) => {
    const url = `${baseUrl}/cart-items`;

    await fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        productId: product.id,
        quantity: 1,
      }),
    });

    // TODO: fetch의 리턴값인 response로 에러 처리!
  };

  return addProductToCart;
}
