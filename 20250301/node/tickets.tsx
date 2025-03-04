/* @jsx createElement */

declare namespace JSX {
  interface IntrinsicElements {
      [elemName: string]: any;
  }
}

function capitalize(text: string) {
  return text.charAt(0).toUpperCase() + text.slice(1);
}

function createElement(
  type: string | Function, props: any, ...children: any[]
) {
  if (typeof type === 'function') {
    return type({ ...props, children });
  }

  const element = document.createElement(type);
  Object.assign(element, props);
  if (props) {
    ['click', 'submit'].forEach((event) => {
      const handler = props[`on${capitalize(event)}`];
      if (handler) {
        element.addEventListener(event, handler);
      }
    });
  }
  children.forEach((child) => {
    if (Array.isArray(child)) {
      child.forEach((childItem) => element.append(childItem));
      return;
    }
    element.append(child);
  });
  return element;
}

interface Ticket {
  id: number;
  title: string;
  description: string;
  status: 'open' | 'closed';
  toggle(): void;
}

function Header() {
  return (
    <header>
      <h1>Tickets</h1>
    </header>
  );
}

function Main({ tickets, addTicket }: {
  tickets: Ticket[];
  addTicket: ({ title, description }: {
    title: string;
    description: string;
  }) => void;
}) {
  return (
    <main>
      <TicketList tickets={tickets} />
      <TicketForm addTicket={addTicket} />
    </main>
  );
}

function TicketList({ tickets }: {
  tickets: Ticket[];
}) {
  return (
    <ul id="ticket-list">
      {tickets.map((ticket) => (
        <TicketItem ticket={ticket} />
      ))}
    </ul>
  );
}

function TicketItem({ ticket }: {
  ticket: Ticket;
}) {
  const handleClick = () => {
    ticket.toggle();
  };

  return (
    <li>
      <div className="title">{ticket.title}</div>
      <div className="description">{ticket.description}</div>
      <button
        className="status"
        onClick={handleClick}
      >
        {ticket.status === 'open' ? 'Open' : 'Closed'}
      </button>
    </li>
  );
}

function TicketForm({ addTicket }: {
  addTicket: ({ title, description }: {
    title: string;
    description: string;
  }) => void;
}) {
  const handleSubmit = (event: Event) => {
    event.preventDefault();

    const form = event.target as HTMLFormElement;
    const formData = new FormData(form);
    const title = formData.get('title') as string;
    const description = formData.get('description') as string;

    addTicket({ title, description });
  };

  return (
    <form id="add-ticket-form" onSubmit={handleSubmit}>
      <div>
        <label for="ticket-title">Title</label>
        <input type="text" name="title" id="ticket-title" placeholder="Title" />
      </div>
      <div>
        <label for="ticket-description">Description</label>
        <textarea name="description" id="ticket-description" placeholder="Description"></textarea>
      </div>
      <button type="submit" id="add-ticket">Add Ticket</button>
    </form>
  );
}

const root = document.getElementById('root');
if (root) {
  const tickets: Ticket[] = [];

  const createTicket = ({ title, description }: {
    title: string;
    description: string;
  }): Ticket => {
    const id = Math.max(...tickets.map((ticket) => ticket.id), 0) + 1;
    return {
      id,
      title,
      description,
      status: 'open',
      toggle() {
        this.status = this.status === 'open' ? 'closed' : 'open';
        render();
      }
    };
  };

  const addTicket = ({ title, description }: {
    title: string;
    description: string;
  }) => {
    const ticket = createTicket({ title, description });
    tickets.push(ticket);
    render();
  }

  const render = () => {
    root.replaceChildren(
      <div>
        <Header />
        <Main tickets={tickets} addTicket={addTicket} />
      </div>
    );
  }

  render();
}
