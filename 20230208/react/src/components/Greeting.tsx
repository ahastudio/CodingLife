import styled from 'styled-components';

const Paragraph = styled.p`
  color: #00F;

  strong {
    font-size: 2em;
    color: #F00;
  }
`;

const BigParagraph = styled(Paragraph)`
  font-size: 2em;

  strong {
    font-size: 1.5em;
  }
`;

function HelloWorld({ className }: React.HTMLAttributes<HTMLElement>) {
  return (
    <BigParagraph className={className}>
      Hello, world
      <strong>!</strong>
    </BigParagraph>
  );
}

const SmallHelloWorld = styled(HelloWorld)`
  font-size: .1em;
`;

export default function Greeting() {
  return (
    <SmallHelloWorld />
  );
}
