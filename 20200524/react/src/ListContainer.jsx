import React from 'react';

import { useDispatch, useSelector } from 'react-redux';

import List from './List';

import {
  deleteTask,
} from './actions';

export default function ListContainer() {
  const { tasks } = useSelector((state) => ({
    tasks: state.tasks,
  }));

  const dispatch = useDispatch();

  function handleClickDelete(id) {
    dispatch(deleteTask(id));
  }

  return (
    <List
      tasks={tasks}
      onClickDelete={handleClickDelete}
    />
  );
}
