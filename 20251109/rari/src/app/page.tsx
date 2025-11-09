import type { PageProps } from 'rari/client';
import ServerTime from '../widgets/server-time/ui/ServerTime';
import Welcome from '../widgets/welcome/ui/Welcome';

export const metadata = {
  title: 'Home | Rari Demo',
  description: 'Home page of the Rari Demo Application',
};

export default function HomePage(_params: PageProps) {
  return (
    <div className="space-y-8">
      <Welcome />
      <ServerTime />
    </div>
  );
}
