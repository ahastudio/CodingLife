test('test', () => {
  //
});

// import { screen, fireEvent } from '@testing-library/react';

// import { render } from '../../../test-helpers';

// import Quantity from './Quantity';

// const store = {
//   quantity: 7,
//   changeQuantity: jest.fn(),
// };

// jest.mock('../../../hooks/useProductFormStore', () => () => [store, store]);

// const context = describe;

// describe('Quantity', () => {
//   beforeEach(() => {
//     jest.clearAllMocks();
//   });

//   it('renders quantity', () => {
//     render(<Quantity />);

//     expect(screen.getByRole('textbox')).toHaveValue('7');
//   });

//   context('when “+” button is clicked', () => {
//     it('calls “changeQuantity” action', () => {
//       render(<Quantity />);

//       fireEvent.click(screen.getByRole('button', { name: '+' }));

//       expect(store.changeQuantity).toBeCalledWith(7 + 1);
//     });
//   });

//   context('when “-” button is clicked', () => {
//     it('calls “changeQuantity” action', () => {
//       render(<Quantity />);

//       fireEvent.click(screen.getByRole('button', { name: '-' }));

//       expect(store.changeQuantity).toBeCalledWith(7 - 1);
//     });
//   });
// });
