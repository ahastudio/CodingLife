import { User } from '../src/types';

const users: User[] = [
  {
    id: 'user-01',
    name: 'Admin',
    email: 'admin@example.com',
    role: 'ROLE_ADMIN',
  },
  {
    id: 'user-02',
    name: 'Tester',
    email: 'tester@example.com',
    role: 'ROLE_USER',
  },
];

export default users;
