import styled from 'styled-components';

import Ticket from '../models/Ticket';

const Container = styled.div`
  ul {
    margin-top: 1rem;
  }

  li {
    margin-block: .5rem;
    border-radius: .2rem;
    box-shadow: .2rem .2rem .4rem #555;
    background: #FFF;
  }

  a {
    display: block;
    padding: 1rem;
    font-size: 2rem;
    text-decoration: none;
    color: #000;
  }
`;

type TicketsProps = {
  tickets: Ticket[];
}

export default function Tickets({ tickets }: TicketsProps) {
  if (!tickets.length) {
    return null;
  }

  return (
    <Container>
      <ul>
        {tickets.map((ticket) => (
          <li key={ticket.id}>
            <a href="/">
              {ticket.title}
            </a>
          </li>
        ))}
      </ul>
    </Container>
  );
}
