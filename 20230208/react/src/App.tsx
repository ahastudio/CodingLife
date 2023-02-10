import { useDarkMode } from 'usehooks-ts';

import { Reset } from 'styled-reset';

import { ThemeProvider } from 'styled-components';

import defaultTheme from './styles/defaultTheme';
import darkTheme from './styles/darkTheme';

import GlobalStyle from './styles/GlobalStyle';

import Greeting from './components/Greeting';
import Switch from './components/Switch';
import Button from './components/Button';

export default function App() {
  const { isDarkMode, toggle: toggleDarkMode } = useDarkMode();

  const theme = isDarkMode ? darkTheme : defaultTheme;

  return (
    <ThemeProvider theme={theme}>
      <Reset />
      <GlobalStyle />
      <Greeting />
      <Switch />
      <Button onClick={toggleDarkMode} active={isDarkMode}>
        Toggle Dark Mode
      </Button>
    </ThemeProvider>
  );
}
