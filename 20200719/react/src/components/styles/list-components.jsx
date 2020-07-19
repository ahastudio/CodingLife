import styled from '@emotion/styled';

export const List = styled.ul({
  display: 'flex',
  flexWrap: 'wrap',
  margin: 0,
  padding: '.4em 0',
  listStyle: 'none',
});

export const Item = styled.li(({ active }) => ({
  marginRight: '1em',
  '& button': {
    marginBottom: '.2em',
    padding: '.4em 1em',
    border: '1px solid #CCC',
    background: active ? '#EEE' : 'transparent',
    color: '#333',
    textDecoration: 'none',
    cursor: 'pointer',
    '&:hover': {
      fontWeight: 'bold',
      color: '#000',
    },
  },
}));
