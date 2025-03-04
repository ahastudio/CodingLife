import express from 'express';
import cors from 'cors';

const HOST = '0.0.0.0';
const PORT = 8080;

const STATUSES = ['open', 'closed'] as const;
type Status = typeof STATUSES[number];

interface Ticket {
  id: number;
  title: string;
  description: string;
  status: Status;
}

const tickets: Ticket[] = [];

const app = express();

app.use(cors());
app.use(express.json());

app.get('/', (req, res) => {
  res.send('Hello, world!');
});

app.get('/tickets', (req, res) => {
  res.send({
    tickets: tickets.map((ticket) => ({
      id: ticket.id,
      title: ticket.title,
      status: ticket.status,
    }))
  });
});

app.post('/tickets', (req, res) => {
  const { title, description } = req.body;

  const ticket: Ticket = {
    id: Math.max(...tickets.map((ticket) => ticket.id), 0) + 1,
    title,
    description,
    status: 'open',
  };

  tickets.push(ticket);

  res.status(201).send({
    id: ticket.id,
    title: ticket.title,
    description: ticket.description,
    status: ticket.status,
  });
});

app.get('/tickets/:id', (req, res) => {
  const id = Number(req.params.id);

  const ticket = tickets.find((ticket) => ticket.id === id);

  if (!ticket) {
    res.status(404).send({});
    return;
  }

  res.send({
    id: ticket.id,
    title: ticket.title,
    description: ticket.description,
    status: ticket.status,
  });
});

// 4. Update
app.patch('/tickets/:id', (req, res) => {
  const id = Number(req.params.id);
  const { title, description, status } = req.body;

  if (!STATUSES.includes(status)) {
    res.status(400).send({ message: 'Invalid status' });
    return;
  }

  const ticket = tickets.find((ticket) => ticket.id === id);

  if (!ticket) {
    res.status(404).send({});
    return;
  }

  ticket.title = title;
  ticket.description = description;
  ticket.status = status;

  res.send({
    id: ticket.id,
    title: ticket.title,
    description: ticket.description,
    status: ticket.status,
  });
});

app.delete('/tickets/:id', (req, res) => {
  const id = Number(req.params.id);

  const index = tickets.findIndex((ticket) => ticket.id === id);

  if (index < 0) {
    res.status(404).send({});
    return;
  }

  tickets.splice(index, 1);

  res.send({});
});

app.listen(PORT, HOST, () => {
  console.log(`Server is running on http://localhost:${PORT}`);
});
