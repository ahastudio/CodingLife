import { useEffect } from 'react';

import Account from '../components/Account';

import useBankStore from '../hooks/useBankStore';

export default function AccountPage() {
  const bankStore = useBankStore();

  useEffect(() => {
    bankStore.fetchAccount();
  }, []);

  return (
    <Account />
  );
}
