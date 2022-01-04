import { gql, useApolloClient, useQuery } from '@apollo/client';

export const GET_FILMS_QUERY = gql`
  query GetAllFilms {
    allFilms {
      films {
        id
        episodeID
        title
        openingCrawl
        created
        edited
      }
    }
  }
`;

type Film = {
  id: string;
  episodeID: number;
  title: string;
  openingCrawl: string;
  created: string;
  edited: string;
}

type AllFilms = {
  allFilms: {
    films: Film[];
  }
}

export default function Films() {
  const client = useApolloClient();

  const { loading, error, data } = useQuery<AllFilms>(GET_FILMS_QUERY);

  const handleClick = (film: Film) => {
    const { cache } = client;
    cache.modify({
      id: cache.identify(film),
      fields: {
        title(cachedTitle) {
          return `${cachedTitle}🌟`;
        },
      },
    });
  };

  if (loading) {
    return <p>Loading...</p>;
  }
  if (error) {
    return <p>Error :(</p>;
  }

  const films = data?.allFilms?.films || [];

  return (
    <ul>
      {films.map((film) => (
        <li key={film.id}>
          [
          {film.episodeID}
          ]
          {' '}
          {film.title}
          {' '}
          <button type="button" onClick={() => handleClick(film)}>
            Modify title!
          </button>
        </li>
      ))}
    </ul>
  );
}
