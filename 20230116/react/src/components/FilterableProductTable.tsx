import { useEffect, useRef, useState } from 'react';

import ProductTable from './ProductTable';
import SearchBar from './SearchBar';

import Product from '../types/Product';

import filterProducts from '../utils/filterProducts';

function useProductsFilter(products: Product[]) {
  const [filterText, setFilterText] = useState<string>('');
  const [inStockOnly, setInStockOnly] = useState<boolean>(false);

  const filteredProducts = filterProducts(products, {
    filterText, inStockOnly,
  });

  return {
    filterText, setFilterText, inStockOnly, setInStockOnly, filteredProducts,
  };
}

type FilterableProductTableProps = {
  products: Product[];
}

export default function FilterableProductTable({
  products,
}: FilterableProductTableProps) {
  const {
    filterText, setFilterText,
    inStockOnly, setInStockOnly,
    filteredProducts,
  } = useProductsFilter(products);

  const query = useRef('');

  useEffect(() => {
    query.current = filterText;
  }, [filterText]);

  useEffect(() => {
    setTimeout(() => {
      console.log(query.current);
    }, 5_000);
  }, []);

  return (
    <div>
      <SearchBar
        filterText={filterText}
        setFilterText={setFilterText}
        inStockOnly={inStockOnly}
        setInStockOnly={setInStockOnly}
      />
      <ProductTable products={filteredProducts} />
    </div>
  );
}
