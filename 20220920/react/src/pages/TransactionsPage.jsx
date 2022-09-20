import { useEffect } from 'react';

import Transactions from '../components/Transactions';

import useBankStore from '../hooks/useBankStore';

export default function TransactionsPage() {
  const bankStore = useBankStore();

  useEffect(() => {
    bankStore.fetchTransactions();
  }, []);

  return (
    <Transactions />
  );
}
