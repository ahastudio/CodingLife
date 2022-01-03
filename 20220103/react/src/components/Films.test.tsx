import { render, waitFor } from '@testing-library/react';
import { MockedProvider } from '@apollo/client/testing';

import Films, { GET_FILMS_QUERY } from './Films';

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

describe('Films', () => {
  function Component() {
    return (
      <MockedProvider mocks={mocks} addTypename={false}>
        <Films />
      </MockedProvider>
    );
  }

  it('renders a list of films', async () => {
    const { container } = render(<Component />);

    expect(container).toHaveTextContent('Loading...');

    await waitFor(() => {
      expect(container).toHaveTextContent('A New Hope');
    });
  });
});
