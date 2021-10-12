import { atom } from 'recoil';

export interface Post {
  id: number;
  title: string;
  body: string;
}

export const postsState = atom<Post[]>({
  key: 'postsState',
  default: [],
});
