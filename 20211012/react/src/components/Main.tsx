import styled from 'styled-components';

import PostForm from './PostForm';
import Posts from './Posts';

const Greeting = styled.p`
  font-size: 2em;
  text-align: center;
  i {
    font-size: 5em;
  }
`;

export default function Main() {
  return (
    <div>
      <Greeting>
        Hello, world
        <i>!</i>
      </Greeting>
      <PostForm />
      <Posts />
    </div>
  );
}
