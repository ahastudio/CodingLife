import { render } from '../../test-helpers';

import Button from './Button';

describe('Button', () => {
  it('renders without crashing', () => {
    const handleClick = jest.fn();

    render((
      <Button onClick={handleClick}>
        확인
      </Button>
    ));
  });
});
