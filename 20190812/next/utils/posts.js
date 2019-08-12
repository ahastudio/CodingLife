const loadPosts = async () => {
  const url = 'https://jsonplaceholder.typicode.com/posts';
  const response = await fetch(url);
  const data = await response.json();
  return data;
};

const loadPost = async (id) => {
  const url = `https://jsonplaceholder.typicode.com/posts/${id}`;
  const response = await fetch(url);
  const data = await response.json();
  return data;
};

export {
  loadPosts,
  loadPost,
};
