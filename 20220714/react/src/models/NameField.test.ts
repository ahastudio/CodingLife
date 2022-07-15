import NameField from './NameField';

const context = describe;

describe('NameField', () => {
  describe('update', () => {
    let nameField: NameField;

    beforeEach(() => {
      nameField = new NameField();
    });

    context('with valid name', () => {
      const name = 'Peter Parker';

      const subject = () => nameField.update(name);

      it('returns new instance with value', () => {
        expect(subject().value).toBe(name);
      });

      it('returns new instance without error', () => {
        expect(subject().error).toBeFalsy();
      });
    });

    context('with invalid name', () => {
      const name = 'x';

      const subject = () => nameField.update(name);

      it('returns new instance with value', () => {
        expect(subject().value).toBe(name);
      });

      it('returns new instance with error', () => {
        expect(subject().error).toBeTruthy();
      });
    });
  });
});
