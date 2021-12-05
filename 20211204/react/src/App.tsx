import Form from './components/Form';
import Tasks from './components/Tasks';
import NewTask from './components/NewTask';

export default function App() {
  return (
    <div>
      <h1>Hello, world!</h1>
      <Form />
      <NewTask />
      <Tasks />
    </div>
  );
}
