import styled from 'styled-components';

const Paragraph = styled.p`
  color: #00F;
`;

const BigParagraph = styled(Paragraph)`
  font-size: 2.4rem;
`;

export default function GreetingSecond() {
  return (
    <BigParagraph>
      Hello, world!
    </BigParagraph>
  );
}
