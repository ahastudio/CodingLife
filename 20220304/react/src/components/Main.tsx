import TaskList from './TaskList';
import TaskForm from './TaskForm';

const { log } = console;

export default function Main() {
  log('Main');

  return (
    <div>
      <h1>Hello, world!</h1>
      <TaskList />
      <TaskForm />
    </div>
  );
}
