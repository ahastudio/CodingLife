import Field from './Field';

export default class NameField implements Field {
  value: string;

  error: string;

  constructor(value?: string, error?: string) {
    this.value = value || '';
    this.error = error || '';
  }

  update(value: string): NameField {
    this.validate(value);
    return new NameField(value.trimStart(), this.error);
  }

  private validate(value: string) {
    this.error = '';
    if (value.length < 2) {
      this.error = '이름을 2글자 이상 입력해 주세요';
    }
  }
}
