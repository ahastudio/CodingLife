import HomePage from '../pages/HomePage';
import AccountPage from '../pages/AccountPage';
import TransferPage from '../pages/TransferPage';
import TransactionsPage from '../pages/TransactionsPage';

export default function useRouteComponent() {
  const { pathname } = window.location;

  const components = {
    '/': HomePage,
    '/account': AccountPage,
    '/transfer': TransferPage,
    '/transactions': TransactionsPage,
  };

  return components[pathname] || HomePage;
}
