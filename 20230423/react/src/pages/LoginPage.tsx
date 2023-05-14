import { useEffect } from 'react';

import { useNavigate } from 'react-router-dom';

import styled from 'styled-components';

import LoginForm from '../components/LoginForm';

import useLoginFormStore from '../hooks/useLoginFormStore';

const Container = styled.div`
  margin: 5rem auto;
  width: 90%;
`;

export default function LoginPage() {
  const navigate = useNavigate();

  const [{ accessToken }, store] = useLoginFormStore();

  useEffect(() => {
    store.reset();
  }, []);

  useEffect(() => {
    if (accessToken) {
      store.reset();
      navigate('/');
    }
  }, [accessToken]);

  return (
    <Container>
      <LoginForm />
    </Container>
  );
}
