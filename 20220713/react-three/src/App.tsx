import { Suspense } from 'react';

import styled from 'styled-components';

import Scene from './components/Scene';
import Loading from './components/Loading';

const Container = styled.div`
  width: 100vw;
  height: 100vh;
`;

export default function App() {
  return (
    <Container>
      <Suspense fallback={<Loading />}>
        <Scene />
      </Suspense>
    </Container>
  );
}
