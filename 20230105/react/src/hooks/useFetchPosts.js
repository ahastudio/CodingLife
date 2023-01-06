import axios from 'axios';

import { useEffect, useState } from 'react';

export default function useFetchPosts() {
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    (async () => {
      setLoading(true);
      try {
        const { data } = await axios.get('http://localhost:8080/posts');
        setPosts(data);
      } catch (e) {
        setError(e);
      }
      setLoading(false);
    })();
  }, []);

  return { loading, error, posts };
}
