import { useEffect } from 'react';

import styled from 'styled-components';

import TextBox from '../ui/TextBox';
import Button from '../ui/Button';

import useAccessToken from '../../hooks/useAccessToken';
import useSignupFormStore from '../../hooks/useSignupFormStore';

const Container = styled.div`
  h2 {
    margin-bottom: 1rem;
    font-size: 4rem;
  }

  button {
    margin-left: 10.5rem;
  }

  p {
    margin-block: 1rem;
  }
`;

export default function SignupForm() {
  const { setAccessToken } = useAccessToken();

  const [{
    email, name, password, passwordConfirmation, valid, error, accessToken,
  }, store] = useSignupFormStore();

  useEffect(() => {
    if (accessToken) {
      setAccessToken(accessToken);
    }
  }, [accessToken]);

  const handleChangeEmail = (value: string) => {
    store.changeEmail(value);
  };

  const handleChangeName = (value: string) => {
    store.changeName(value);
  };

  const handleChangePassword = (value: string) => {
    store.changePassword(value);
  };

  const handleChangePasswordConfirmation = (value: string) => {
    store.changePasswordConfirmation(value);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    store.signup();
  };

  return (
    <Container>
      <h2>회원 가입</h2>
      <form onSubmit={handleSubmit}>
        <TextBox
          label="E-mail"
          placeholder="tester@example.com"
          value={email}
          onChange={handleChangeEmail}
        />
        <TextBox
          label="Name"
          value={name}
          onChange={handleChangeName}
        />
        <TextBox
          label="Password"
          type="password"
          value={password}
          onChange={handleChangePassword}
        />
        <TextBox
          label="Password Confirmation"
          type="password"
          value={passwordConfirmation}
          onChange={handleChangePasswordConfirmation}
        />
        <Button type="submit" disabled={!valid}>
          회원 가입
        </Button>
        {error && (
          <p>회원 가입 실패</p>
        )}
      </form>
    </Container>
  );
}
