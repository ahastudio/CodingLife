import styled, { css } from 'styled-components';

import { useBoolean } from 'usehooks-ts';

type ButtonProps = {
  type?: 'button' | 'submit' | 'reset';
  active?: boolean;
}

const Button = styled.button.attrs<ButtonProps>((props) => ({
  type: props.type ?? 'button',
}))<ButtonProps>`
  background: #FFF;
  color: #000;
  border: 1px solid ${(props) => (props.active ? '#F00' : '#888')};

  ${(props) => props.active && css`
    background: #00F;
    color: #FFF;
  `}
`;

const PrimaryButton = styled(Button)`
  background: #EEE;

  ${(props) => props.active && css`
    background: #F0F;
  `}
`;

export default function Switch() {
  const { value: active, toggle } = useBoolean(false);

  return (
    <PrimaryButton onClick={toggle} active={active}>
      On/Off
    </PrimaryButton>
  );
}
