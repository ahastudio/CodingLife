import { screen } from '@testing-library/react';

import { setRecoil } from 'recoil-nexus';

import { renderScreen } from '../test-helper';

import Posts from './Posts';

import { postsState } from '../states/blog';

const context = describe;

describe('Posts', () => {
  context('with posts', () => {
    it('renders a list of post', () => {
      renderScreen(() => <Posts />);

      setRecoil(postsState, [
        { id: 1, title: 'Title', body: 'Body' },
      ]);

      screen.getByText(/Title/);
      screen.getByText(/Body/);
    });
  });

  context('without posts', () => {
    it('renders “Empty” message', () => {
      renderScreen(() => <Posts />);

      setRecoil(postsState, []);

      screen.getByText(/Empty/);
    });
  });
});
