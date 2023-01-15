import Product from '../types/Product';

export default function selectProducts(
  products: Product[],
  cateogry: string,
): Product[] {
  return products.filter((product) => product.category === cateogry);
}
