import { useLocalStorage } from 'usehooks-ts';

import Button from '../components/ui/Button';

import { bankStore } from '../stores/BankStore';

export default function HomePage() {
  const [themeName, setThemeName] = useLocalStorage('theme', 'default');

  const toggleTheme = () => {
    setThemeName(themeName === 'default' ? 'dark' : 'default');
  };

  const handleLogin = () => {
    bankStore.login({ accountNumber: '1234', password: 'password' });
  };

  return (
    <div>
      <p>Hello, world!</p>
      <Button type="button" onClick={toggleTheme}>
        Toggle theme
      </Button>
      <Button type="button" onClick={handleLogin}>
        Log in
      </Button>
    </div>
  );
}
