/* eslint-disable import/prefer-default-export */

import { atom } from 'recoil';

import Task from '../models/Task';

export const tasksState = atom<Task[]>({
  key: 'todo/tasksState',
  default: [],
});
