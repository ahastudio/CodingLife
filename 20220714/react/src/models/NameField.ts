/* eslint-disable class-methods-use-this */

import Field from './Field';

export default class NameField implements Field {
  readonly value: string;

  readonly error: string;

  constructor(value?: string, error?: string) {
    this.value = value || '';
    this.error = error || '';
  }

  update(value: string): NameField {
    return new NameField(value.trimStart(), this.validate(value));
  }

  validate(value: string): string {
    if (value.length < 2) {
      return '이름을 2글자 이상 입력해 주세요';
    }
    return '';
  }
}
