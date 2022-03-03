import { render } from '@testing-library/react';

import { RecoilRoot } from 'recoil';

import StatesUpdater from './StatesUpdater';

describe('StatesUpdater', () => {
  it('renders without crashing', () => {
    render((
      <RecoilRoot>
        <StatesUpdater />
      </RecoilRoot>
    ));
  });
});
