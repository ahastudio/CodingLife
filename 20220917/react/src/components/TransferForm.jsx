import { useState } from 'react';

import { useForm } from 'react-hook-form';

import styled from 'styled-components';

import PrimaryButton from './ui/PrimaryButton';

import useBankStore from '../hooks/useBankStore';

const Error = styled.div`
  font-weight: bold;
  color: #F00;
`;

export default function TransferForm() {
  const bankStore = useBankStore();

  const { register, handleSubmit, formState: { errors } } = useForm();

  const onSubmit = async (data) => {
    const { accountNumber, amount, name } = data;
    bankStore.requestTransfer({ to: accountNumber, amount, name });
  };

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <div>
        <label htmlFor="input-account">
          받을 분 계좌 번호
        </label>
        <input
          id="input-account"
          // eslint-disable-next-line react/jsx-props-no-spreading
          {...register('accountNumber', { required: true })}
        />
        {errors.accountNumber ? (
          <Error>받으실 분 계좌 번호를 입력해 주세요</Error>
        ) : null}
      </div>
      <div>
        <label htmlFor="input-amount">
          보낼 금액
        </label>
        <input
          id="input-amount"
          type="number"
          // eslint-disable-next-line react/jsx-props-no-spreading
          {...register('amount', { required: true })}
        />
        {errors.amount ? (
          <Error>금액을 입력해 주세요</Error>
        ) : null}
      </div>
      <div>
        <label htmlFor="input-name">
          받는 분께 표시할 이름
        </label>
        <input
          id="input-name"
          type="text"
          // eslint-disable-next-line react/jsx-props-no-spreading
          {...register('name', { required: true })}
        />
        {errors.name ? (
          <Error>이름을 입력해 주세요</Error>
        ) : null}
      </div>
      <PrimaryButton type="submit" disabled={bankStore.isTransferProcessing}>
        보내기
      </PrimaryButton>
      {bankStore.isTransferProcessing ? (
        <p>계좌 이체 요청 중</p>
      ) : null}
      {bankStore.isTransferSuccess ? (
        <p>계좌 이체 성공</p>
      ) : null}
      {bankStore.isTransferFail ? (
        <Error>
          <p>계좌 이체 실패</p>
          <p>{bankStore.errorMessage}</p>
        </Error>
      ) : null}
    </form>
  );
}
