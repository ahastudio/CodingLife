import Layout from './components/Layout';

import HomePage from './pages/HomePage';
import AboutPage from './pages/AboutPage';
import LogoutPage from './pages/LogoutPage';

const routes = [
  {
    element: <Layout />,
    children: [
      { path: '/', element: <HomePage /> },
      { path: '/about', element: <AboutPage /> },
      { path: '/logout', element: <LogoutPage /> },
    ],
  },
];

export default routes;
