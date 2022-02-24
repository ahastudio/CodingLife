import { screen, fireEvent } from '@testing-library/react';

import { getRecoil } from 'recoil-nexus';

import { renderScreen } from '../test-helper';

import PostForm from './PostForm';

import { postsState } from '../states/blog';

const context = describe;

describe('PostForm', () => {
  context('when the “add” button is clicked', () => {
    it('adds a new post', () => {
      renderScreen(() => <PostForm />);

      const oldCount = getRecoil(postsState).length;

      fireEvent.click(screen.getByText('Add post!'));

      const newCount = getRecoil(postsState).length;

      expect(newCount).toBe(oldCount + 1);
    });
  });
});
