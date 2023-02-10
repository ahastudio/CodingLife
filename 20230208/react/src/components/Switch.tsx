import { useBoolean } from 'usehooks-ts';

import PrimaryButton from './PrimaryButton';

export default function Switch() {
  const { value: active, toggle } = useBoolean(false);

  return (
    <PrimaryButton onClick={toggle} active={active}>
      On/Off
    </PrimaryButton>
  );
}
