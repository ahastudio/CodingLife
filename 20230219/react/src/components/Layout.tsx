import { Outlet } from 'react-router-dom';

import styled from 'styled-components';

import Header from './Header';

const Container = styled.div`
  margin-inline: auto;
  width: 90%;
`;

export default function Layout() {
  return (
    <Container>
      <Header />
      <Outlet />
    </Container>
  );
}
