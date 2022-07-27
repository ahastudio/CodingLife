import { render, screen, fireEvent } from '@testing-library/react';

import PostForm from './PostForm';

const mockSave = jest.fn();

jest.mock('../hooks/usePostStore', () => () => ({
  save: mockSave,
}));

test('PostForm', async () => {
  render(<PostForm />);

  fireEvent.change(screen.getByLabelText('작성자'), {
    target: { value: '작성자' },
  });

  fireEvent.change(screen.getByLabelText('제목'), {
    target: { value: '제목' },
  });

  fireEvent.change(screen.getByLabelText('내용'), {
    target: { value: '내용' },
  });

  fireEvent.click(screen.getByText('등록'));

  expect(mockSave).toBeCalledWith({
    author: '작성자',
    title: '제목',
    body: '내용',
  });
});
