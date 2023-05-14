import { screen } from '@testing-library/react';

import { render } from '../test-helpers';

import Description from './Description';

const context = describe;

describe('Description', () => {
  context('when text is empty', () => {
    const text = '';

    it('renders nothing', () => {
      const { container } = render(<Description value={text} />);

      expect(container).toBeEmptyDOMElement();
    });
  });

  context('when text is not empty', () => {
    const lines = ['1st line', '2nd line', '3rd line'];
    const text = lines.join('\n');

    it('renders multi-line text', () => {
      render(<Description value={text} />);

      lines.forEach((line) => {
        screen.getByText(line);
      });
    });
  });
});
