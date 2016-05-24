export class ApplicationPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('application-app h1')).getText();
  }
}
