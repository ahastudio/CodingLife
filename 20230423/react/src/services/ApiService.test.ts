import ApiService from './ApiService';

describe('ApiService', () => {
  let apiService: ApiService;

  beforeEach(() => {
    apiService = new ApiService();
  });

  test('setAccessToken', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');
    apiService.setAccessToken('');
    apiService.setAccessToken('ACCESS-TOKEN');
  });

  test('login', async () => {
    const accessToken = await apiService.login({
      email: 'tester@example.com',
      password: 'password',
    });
    expect(accessToken).toBeTruthy();
  });

  test('logout', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    await apiService.logout();
  });

  test('fetchCurrentUser', async () => {
    apiService.setAccessToken('ACCESS-TOKEN');

    const user = await apiService.fetchCurrentUser();

    expect(user.id).toBeTruthy();
    expect(user.name).toBeTruthy();
  });
});
