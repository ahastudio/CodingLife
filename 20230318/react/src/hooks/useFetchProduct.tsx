import data from '../data';

export default function useFetchProduct(id?: string) {
  if (!id) {
    return null;
  }

  return data.products.find((i) => i.id === id);
}
