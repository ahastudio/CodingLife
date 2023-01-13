import Product from '../types/Product';

function normalize(text: string) {
  return text.trim().toLocaleLowerCase();
}

type FilterConditions = {
  filterText: string;
  inStockOnly: boolean;
}

export default function filterProducts(
  products: Product[],
  { filterText, inStockOnly }: FilterConditions,
): Product[] {
  const filteredProducts = products
    .filter((product) => !inStockOnly || product.stocked);

  const query = normalize(filterText);

  if (!query) {
    return filteredProducts;
  }

  const contains = (product: Product) => (
    normalize(product.name).includes(query)
  );

  return filteredProducts.filter(contains);
}
