function url(path: string) {
  const baseUrl = process.env.API_BASE_URL || 'http://localhost:3000';
  return `${baseUrl}${path}`;
}

export type Post = {
  id: number;
  title: string;
  body: string;
}

export async function fetchPosts() {
  const response = await fetch(url('/posts'));
  const data = await response.json();
  return data;
}
