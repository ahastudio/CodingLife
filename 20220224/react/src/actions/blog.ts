/* eslint-disable import/prefer-default-export */

import { getRecoil, setRecoil } from 'recoil-nexus';
import { postsState } from '../states/blog';

export function addPost() {
  const posts = getRecoil(postsState);

  const post = {
    id: new Date().getTime(),
    title: 'What time is it?',
    body: `It's ${new Date()}`,
  };

  setRecoil(postsState, [post, ...posts]);
}
