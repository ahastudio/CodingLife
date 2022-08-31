import styled from 'styled-components';

import Button from './Button';

const SecondaryButton = styled(Button)`
  background: ${(props) => props.theme.colors.secondary};
  color: ${(props) => props.theme.colors.secondaryText};
`;

export default SecondaryButton;
