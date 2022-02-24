import { screen } from '@testing-library/react';

import { renderScreen } from '../test-helper';

import Main from './Main';

describe('Main', () => {
  it('renders greeting message', () => {
    renderScreen(() => <Main />);

    screen.getByText(/Hello, world/);
  });
});
