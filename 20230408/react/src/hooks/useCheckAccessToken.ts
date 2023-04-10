import { useEffect } from 'react';

import useAccessToken from './useAccessToken';

import { apiService } from '../services/ApiService';

export default function useCheckAccessToken(): void {
  const { accessToken, setAccessToken } = useAccessToken();

  useEffect(() => {
    const fetchCurrentUser = async () => {
      try {
        await apiService.fetchCurrentUser();
      } catch (e) {
        setAccessToken('');
      }
    };

    fetchCurrentUser();
  }, [accessToken, setAccessToken]);
}
