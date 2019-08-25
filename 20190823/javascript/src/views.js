const render = state => {
  const { tasks } = state;
  return `
    <h1>ToDo</h1>
    ${renderTasks(tasks)}
    ${renderForm()}
  `;
};

const renderTasks = tasks => {
  if (tasks.length == 0) {
    return '';
  }
  return `
    <ul>
      ${tasks.map(task => `
        <li>
          <input type="checkbox" ${task.completed ? 'checked' : ''}
            class="checkbox-task" data-task-id="${task.id}"
          >
          ${task.title}
        </li>
      `).join('')}
    </ul>
  `;
};

const renderForm = () => {
  return `
    <p>
      <input type="text" id="input-task-title">
      <button type="button" id="button-add-task">Add</button>
    </p>
  `;
};

export {
  render,
}
