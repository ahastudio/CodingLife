import { render } from '@testing-library/react';

import { RecoilRoot } from 'recoil';

import Main from './Main';

describe('Main', () => {
  it('renders greeting message', () => {
    const { container } = render((
      <RecoilRoot>
        <Main />
      </RecoilRoot>
    ));

    expect(container).toHaveTextContent('Hello, world!');
  });
});
