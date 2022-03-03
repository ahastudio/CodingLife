import { render, screen } from '@testing-library/react';

import { RecoilRoot } from 'recoil';

import Main from './Main';

describe('Main', () => {
  it('renders greeting message', () => {
    render((
      <RecoilRoot>
        <Main />
      </RecoilRoot>
    ));

    screen.getByText(/Hello, world/);
  });
});
