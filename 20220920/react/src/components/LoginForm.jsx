import { useNavigate } from 'react-router-dom';

import { useLocalStorage } from 'usehooks-ts';

import { useForm } from 'react-hook-form';

import PrimaryButton from './ui/PrimaryButton';

import useBankStore from '../hooks/useBankStore';

export default function LoginForm() {
  const navigate = useNavigate();

  const [, setAccessToken] = useLocalStorage('accessToken', '');

  const bankStore = useBankStore();

  const { register, handleSubmit } = useForm();

  const onSubmit = async (data) => {
    const { accountNumber, password } = data;
    const accessToken = await bankStore.login({ accountNumber, password });
    if (accessToken) {
      setAccessToken(accessToken);
      navigate('/');
    }
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <h2>로그인</h2>
      <div>
        <label htmlFor="input-account-number">
          계좌 번호
        </label>
        <input
          id="input-account-number"
          // eslint-disable-next-line react/jsx-props-no-spreading
          {...register('accountNumber', { required: true })}
        />
      </div>
      <div>
        <label htmlFor="input-password">
          패스워드
        </label>
        <input
          id="input-password"
          type="password"
          // eslint-disable-next-line react/jsx-props-no-spreading
          {...register('password', { required: true })}
        />
      </div>
      <PrimaryButton type="submit" onClick={() => {}}>
        로그인
      </PrimaryButton>
    </form>
  );
}
