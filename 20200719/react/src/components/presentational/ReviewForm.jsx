import React from 'react';

import styled from '@emotion/styled';

import TextField from './TextField';
import StarsField from './StarsField';

const Container = styled.section({
  marginTop: '2em',
});

const Button = styled.button({
  fontSize: '1em',
  display: 'inline-block',
  padding: '.5em 1em',
});

function ReviewForm({ fields, onChange, onSubmit }) {
  const { score, description } = fields;

  return (
    <Container>
      <h3>
        소중한 리뷰를 남겨주세요
      </h3>
      <StarsField
        label="평점"
        name="score"
        value={score}
        onChange={onChange}
      />
      <TextField
        label="리뷰 내용"
        name="description"
        value={description}
        onChange={onChange}
      />
      <Button
        type="button"
        onClick={onSubmit}
      >
        리뷰 남기기
      </Button>
    </Container>
  );
}

export default React.memo(ReviewForm);
