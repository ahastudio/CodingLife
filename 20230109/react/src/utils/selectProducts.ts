import Product from '../types/Product';

function select<ItemType, ValueType>(
  items: ItemType[],
  key: keyof ItemType,
  value: ValueType,
) {
  return items.filter((item) => item[key] === value);
}

export default function selectProducts(
  products: Product[],
  cateogry: string,
): Product[] {
  return select(products, 'category', cateogry);
}
