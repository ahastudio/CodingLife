import { useDarkMode } from 'usehooks-ts';

import { ThemeProvider } from 'styled-components';

import { Reset } from 'styled-reset';

import defaultTheme from './styles/defaultTheme';
import darkTheme from './styles/darkTheme';

import GlobalStyle from './styles/GlobalStyle';

import Button from './components/Button';

import GreetingFirst from './components/GreetingFirst';
import GreetingSecond from './components/GreetingSecond';
import GreetingThird from './components/GreetingThird';
import GreetingForth from './components/GreetingForth';

export default function App() {
  const { isDarkMode, toggle } = useDarkMode();

  const theme = isDarkMode ? darkTheme : defaultTheme;

  return (
    <ThemeProvider theme={theme}>
      <Reset />
      <GlobalStyle />
      <div>
        <h1>Demo</h1>
        <Button onClick={toggle}>
          DarkMode On/Off
        </Button>
        <GreetingFirst />
        <GreetingSecond />
        <GreetingThird />
        <GreetingForth />
      </div>
    </ThemeProvider>
  );
}
