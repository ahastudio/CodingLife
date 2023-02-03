import { useBoolean } from 'usehooks-ts';

import styled, { css } from 'styled-components';

import Button from './Button';

type ParagraphProps = {
  active?: boolean;
}

const Paragraph = styled.p<ParagraphProps>`
  color: ${(props) => (props.active ? '#F00' : '#888')};
  ${(props) => props.active && css`
    font-weight: bold;
  `}
`;

export default function GreetingForth() {
  const { value: active, toggle } = useBoolean(false);

  return (
    <div>
      <Paragraph>
        Inactive
      </Paragraph>
      <Paragraph active>
        Active
      </Paragraph>
      <Paragraph active={active}>
        Hello, world
        {' '}
        <Button onClick={toggle}>
          Toggle
        </Button>
      </Paragraph>
    </div>
  );
}
