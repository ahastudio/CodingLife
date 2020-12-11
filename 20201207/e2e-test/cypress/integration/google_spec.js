describe('Google', () => {
  it('Visits and searches “Cypress”', () => {
    cy.visit('https://www.google.com/ncr');
    cy.get('[name="q"]').type('Cypress');
    cy.contains('Google Search').click();
    cy.contains('www.cypress.io').should('be.visible');
  });
});
