import Product from '../types/Product';

export default function selectProducts(
  items: Product[],
  cateogry: string,
): Product[] {
  return items.filter((item) => item.category === cateogry);
}
