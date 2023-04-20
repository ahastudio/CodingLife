/* eslint-disable import/no-extraneous-dependencies */

import '@testing-library/jest-dom';

import 'whatwg-fetch';

import 'reflect-metadata';

import server from './mocks/server';

beforeAll(() => server.listen({ onUnhandledRequest: 'error' }));

afterAll(() => server.close());

afterEach(() => server.resetHandlers());

class FakeDaumPostcode {
  oncomplete: (data: unknown) => void;

  size: Record<string, unknown>;

  constructor({ oncomplete, width, height }: {
    oncomplete: (data: unknown) => void;
    width: string;
    height: string;
  }) {
    this.oncomplete = oncomplete;
    this.size = { width, height };
  }

  // eslint-disable-next-line class-methods-use-this
  embed(element: HTMLElement) {
    // eslint-disable-next-line no-param-reassign
    element.innerHTML = `
      <legend>우편번호 검색 입력폼</legend>
      <button data-testid="daum-postcode-test-button">테스트 버튼</button>
    `;

    element.addEventListener('click', () => {
      this.oncomplete({
        address: '도로명 주소',
        zonecode: '우편번호',
      });
    });
  }
}

beforeEach(() => {
  window.daum = {
    Postcode: FakeDaumPostcode,
  };
});
