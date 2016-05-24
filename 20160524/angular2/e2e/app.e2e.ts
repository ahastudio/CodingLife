import { ApplicationPage } from './app.po';

describe('application App', function() {
  let page: ApplicationPage;

  beforeEach(() => {
    page = new ApplicationPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('application works!');
  });
});
