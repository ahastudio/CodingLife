import { Reset } from 'styled-reset';

import GlobalStyle from './styles/GlobalStyle';

import Board from './components/Board';

export default function App() {
  return (
    <>
      <Reset />
      <GlobalStyle />
      <Board />
    </>
  );
}
