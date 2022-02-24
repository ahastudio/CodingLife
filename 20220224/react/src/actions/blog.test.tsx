import { render, act } from '@testing-library/react';

import { RecoilRoot } from 'recoil';
import RecoilNexus, { getRecoil } from 'recoil-nexus';

import { addPost } from './blog';

import { postsState } from '../states/blog';

function renderScreen() {
  render((
    <RecoilRoot>
      <RecoilNexus />
    </RecoilRoot>
  ));
}

describe('addPost', () => {
  it('adds a new post', () => {
    renderScreen();

    const oldCount = getRecoil(postsState).length;

    act(() => {
      addPost();
    });

    const newCount = getRecoil(postsState).length;

    expect(newCount).toBe(oldCount + 1);
  });
});
