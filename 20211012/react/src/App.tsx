import styled from 'styled-components';

const Greeting = styled.p`
  font-size: 2em;
  text-align: center;
  i {
    font-size: 5em;
  }
`;

export default function App() {
  return (
    <Greeting>
      Hello, world
      <i>!</i>
    </Greeting>
  );
}
