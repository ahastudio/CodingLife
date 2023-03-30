const { I } = inject();

const BACKDOOR_BASE_URL = process.env.BACKDOOR_BASE_URL
                          || 'http://localhost:3000/backdoor';

export = {
  setupDatabase() {
    I.amOnPage(`${BACKDOOR_BASE_URL}/setup-database`);
    I.see('OK');
  },
}
