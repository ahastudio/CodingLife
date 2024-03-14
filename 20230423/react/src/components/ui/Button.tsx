import styled from 'styled-components';

type ButtonProps = {
  type?: 'button' | 'submit' | 'reset';
  leftpad?: boolean;
}

const Button = styled.button.attrs<ButtonProps>((props) => ({
  type: props.type ?? 'button',
}))<ButtonProps>`
  margin-bottom: .5rem;
  margin-left: ${(props) => (props.leftpad ? props.theme.sizes.labelWidth : 0)};
  border: .1rem solid ${(props) => props.theme.colors.primary};
  background: transparent;
  color: ${(props) => props.theme.colors.primary};
  cursor: pointer;

  :disabled {
    filter: grayscale(80%);
    cursor: not-allowed;
  }
`;

export default Button;
