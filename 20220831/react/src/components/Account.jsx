import useBankStore from '../hooks/useBankStore';

import numberFormat from '../utils/numberFormat';

export default function Account() {
  const bankStore = useBankStore();

  return (
    <div>
      <p>
        이름:
        {' '}
        {bankStore.name}
      </p>
      <p>
        계좌번호:
        {' '}
        {bankStore.accountNumber}
      </p>
      {bankStore.amount > 0 ? (
        <p>
          잔액:
          {' '}
          {numberFormat(bankStore.amount)}
          원
        </p>
      ) : (
        <p>잔액이 없습니다</p>
      )}
    </div>
  );
}
