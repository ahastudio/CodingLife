import styled from 'styled-components';

function HelloWorld({ className }: React.HTMLAttributes<HTMLElement>) {
  return (
    <p className={className}>
      Hello, world!
    </p>
  );
}

const GreetingThird = styled(HelloWorld)`
  color: #00F;
`;

export default GreetingThird;
