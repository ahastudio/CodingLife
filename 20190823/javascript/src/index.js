import { render } from './views';
import { initialState, addTask, toggleTask } from './services';

const $ = id => document.getElementById(id);
const $$ = selector => document.querySelectorAll(selector);

const update = state => {
  $('app').innerHTML = render(state);

  $('button-add-task').addEventListener('click', () => {
    const title = $('input-task-title').value;
    const newState = addTask(state, title);
    update(newState);
  });

  $$('.checkbox-task').forEach(el =>
    el.addEventListener('click', event => {
      const { taskId } = event.target.dataset;
      const newState = toggleTask(state, +taskId);
      update(newState);
    })
  );
};

update(initialState);
