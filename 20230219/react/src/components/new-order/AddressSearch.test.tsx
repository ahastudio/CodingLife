import { screen, fireEvent } from '@testing-library/react';

import { render } from '../../test-helpers';

import AddressSearch from './AddressSearch';

test('AddressSearch', () => {
  const close = jest.fn();
  const changeAddress = jest.fn();

  render((
    <AddressSearch
      close={close}
      changeAddress={changeAddress}
    />
  ));

  screen.getByText('우편번호 검색 입력폼');

  fireEvent.click(screen.getByTestId('daum-postcode-test-button'));

  expect(changeAddress).toBeCalled();
  expect(close).toBeCalled();
});
