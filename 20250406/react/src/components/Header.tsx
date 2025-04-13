import Link from 'next/link';

export default function Header() {
  return (
    <header>
      <h1>Tickets</h1>
      <nav>
        <ul>
          <li>
            <Link href="/">Home</Link>
          </li>
          <li>
            <Link href="/tickets">Tickets</Link>
          </li>
        </ul>
      </nav>
    </header>
  );
}
