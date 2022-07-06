import styled from 'styled-components';

const List = styled.ul`
  margin: 0;
  padding: 0;
  list-style: none;

  li {
    margin-block: .4em;
    padding: .6em;
    border: 1px solid #CCC;
    border-radius: 4px;
  }
`;

export default function ToDoList({ tasks }) {
  return (
    <List>
      {tasks.map((task) => (
        <li key={task.id}>
          {task.title}
        </li>
      ))}
    </List>
  );
}
