export async function fetchCategories() {
  const url = 'https://eatgo-customer-api.ahastudio.com/categories';
  const response = await fetch(url);
  const data = await response.json();
  return data;
}

export async function fetchRegions() {
  const url = 'https://eatgo-customer-api.ahastudio.com/regions';
  const response = await fetch(url);
  const data = await response.json();
  return data;
}

export async function fetchRestaurants({ categoryId, region }) {
  const baseUrl = 'https://eatgo-customer-api.ahastudio.com';
  const url = `${baseUrl}/restaurants?category=${categoryId}&region=${region}`;
  const response = await fetch(url);
  const data = await response.json();
  return data;
}
