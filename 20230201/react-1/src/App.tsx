import Header from './components/Header';
import Footer from './components/Footer';

import AboutPage from './pages/AboutPage';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';

const pages = {
  '/': HomePage,
  '/about': AboutPage,
  '/login': LoginPage,
};

export default function App() {
  const { pathname } = window.location;

  const Page = Reflect.get(pages, pathname) || HomePage;

  return (
    <div>
      <Header />
      <main>
        <Page />
      </main>
      <Footer />
    </div>
  );
}
