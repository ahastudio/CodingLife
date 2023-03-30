const { I } = inject();

const BACKDOOR_BASE_URL = 'https://shop-demo-api-01.fly.dev/backdoor';

export = {
  setupDatabase() {
    I.amOnPage(`${BACKDOOR_BASE_URL}/setup-database`);
    I.see('OK');
  },
}
