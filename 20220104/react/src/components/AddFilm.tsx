import { useApolloClient } from '@apollo/client';

import { GET_FILMS_QUERY } from './Films';

let newId = 10;

function generateFilm() {
  const now = new Date();

  newId += 1;

  return {
    id: newId,
    episodeID: new Date().getTime(),
    title: `Title - ${now}`,
    openingCrawl: '',
    created: now,
    edited: now,
  };
}

export default function AddFilm() {
  const client = useApolloClient();

  const handleClick = () => {
    const newFilm = generateFilm();

    const { cache } = client;
    cache.updateQuery({
      query: GET_FILMS_QUERY,
    }, (data) => ({
      ...data,
      allFilms: {
        ...data.allFilms,
        films: [...data.allFilms.films, {
          __typename: 'Film',
          ...newFilm,
        }],
      },
    }));
  };

  return (
    <div>
      <button type="button" onClick={handleClick}>
        Add film
      </button>
    </div>
  );
}
