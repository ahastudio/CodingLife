import { test, expect } from '@playwright/test';

test('Show all products', async ({ page }) => {
  await page.goto('/');

  await expect(page.getByText('Apple')).toBeVisible();
  await expect(page.getByText('Grape')).toBeHidden();
});

test('Filter products', async ({ page }) => {
  await page.goto('/');

  await expect(page.getByText('Apple')).toBeVisible();

  const searchInput = page.getByLabel('Search');

  await searchInput.fill('a');

  await expect(page.getByText('Apple')).toBeVisible();

  await searchInput.fill('aa');

  await expect(page.getByText('Apple')).toBeHidden();
});

test('Click the “Increase” button', async ({ page }) => {
  await page.goto('/');

  const count = 13;

  await Promise.all((
    [...Array(count)].map(async () => {
      await page.getByText('Increase').click();
    })
  ));

  await expect(page.getByText(`${count}`)).toBeVisible();
});
