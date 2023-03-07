import styled from 'styled-components';

import Tickets from './Tickets';

import useFilterTickets from '../hooks/useFilterTickets';

const STATUSES = ['todo', 'doing', 'done'];

const Container = styled.div`
  margin-block: 1rem;
  margin-inline: auto;
  width: 90%;
  max-width: 1000px;

  h1 {
    text-align: center;
    font-size: 4rem;
  }

  > ul {
    display: flex;
    flex-direction: row;
    justify-content: space-between;

    > li {
      margin: .5rem;
      padding: 1rem;
      width: 100%;
      border-radius: .2rem;
      box-shadow: .1rem .1rem .4rem #000;
      background: #888;

      h2 {
        text-shadow:
          -.1rem -.1rem .1rem #555,
          -.1rem +.1rem .1rem #555,
          +.1rem -.1rem .1rem #555,
          +.1rem +.1rem .1rem #555;
        text-transform: capitalize;
        font-size: 2.5rem;
        color: #FFF;
      }
    }
  }
`;

export default function Board() {
  const filterTickets = useFilterTickets();

  return (
    <Container>
      <h1>Board</h1>
      <ul>
        {STATUSES.map((status) => (
          <li key={status}>
            <h2>{status}</h2>
            <Tickets tickets={filterTickets({ status })} />
          </li>
        ))}
      </ul>
    </Container>
  );
}
