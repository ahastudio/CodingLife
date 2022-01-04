import { render, fireEvent } from '@testing-library/react';
import { MockedProvider } from '@apollo/client/testing';
import { InMemoryCache } from '@apollo/client';

import AddFilm from './AddFilm';
import { GET_FILMS_QUERY } from './Films';

const context = describe;

const cache = new InMemoryCache();

const mocks = [
  {
    request: {
      query: GET_FILMS_QUERY,
      variables: {},
    },
    result: {
      data: {
        allFilms: {
          films: [
            {
              __typename: 'Film',
              id: 'ID-0001',
              episodeID: 4,
              title: 'A New Hope',
              openingCrawl: 'It is a period of civil war. blah blah',
              created: '2014-12-10T14:23:31.880000Z',
              edited: '2014-12-20T19:49:45.256000Z',
            },
          ],
        },
      },
    },
  },
];

describe('AddFilm', () => {
  function Component() {
    return (
      <MockedProvider mocks={mocks} cache={cache}>
        <AddFilm />
      </MockedProvider>
    );
  }

  it('renders “Add film” button', async () => {
    const { container } = render(<Component />);

    expect(container).toHaveTextContent('Add film');
  });

  context('when Add film” button is clicked', () => {
    const [mock] = mocks;

    beforeEach(() => {
      const { query } = mock.request;
      cache.updateQuery({ query }, () => mock.result.data);
    });

    it('changes title of a film', async () => {
      const { getByText } = render(<Component />);

      fireEvent.click(getByText('Add film'));

      const data: any = cache.readQuery({ query: GET_FILMS_QUERY });

      expect(data.allFilms.films)
        .toHaveLength(mock.result.data.allFilms.films.length + 1);
    });
  });
});
