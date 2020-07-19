import React from 'react';

import styled from '@emotion/styled';

const List = styled.ul({
  margin: '1em 0',
  padding: 0,
  borderTop: '1px solid #DDD',
  listStyle: 'none',
});

const Item = styled.li({
  padding: '1em 0',
  borderBottom: '1px solid #DDD',
});

function Reviews({ reviews }) {
  if (!reviews || !reviews.length) {
    return null;
  }

  const sortedReviews = [...reviews].sort((a, b) => b.id - a.id);

  const score = (review) => Math.max(0, Math.min(review.score, 5));

  return (
    <List>
      {sortedReviews.map((review) => (
        <Item key={review.id}>
          [
          {'★'.repeat(score(review))}
          {'☆'.repeat(5 - score(review))}
          ]
          {' '}
          {review.name}
          :
          {' '}
          {review.description}
        </Item>
      ))}
    </List>
  );
}

export default React.memo(Reviews);
