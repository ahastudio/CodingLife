import styled from 'styled-components';

const Container = styled.div`
  font-size: 10vw;
  font-family: cursive;
  z-index: 100;
  position: fixed;
  top: 0;
  left: 0;
  width: 100vw;
  height: 100vh;
  line-height: 100vh;
  text-align: center;
  background: #000;
  color: #FFF;
`;

export default function Loading() {
  return (
    <Container>
      Loading...
    </Container>
  );
}
