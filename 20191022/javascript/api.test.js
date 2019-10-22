const frisby = require('frisby');
const { Joi } = frisby;

test('GET /categories', async () => {
  const url = 'https://eatgo-customer-api.ahastudio.com/categories';
  await frisby.get(url)
    .expect('status', 200)
    .expect('jsonTypes', '*', {
      'id': Joi.number().required(),
      'name': Joi.string().required(),
    });
});

test('GET /restaurants', async () => {
  const url = 'https://eatgo-customer-api.ahastudio.com/restaurants' +
              '?region=&category=1';
  await frisby.get(url)
    .expect('status', 200)
    .expect('jsonTypes', '*', {
      'id': Joi.number().required(),
      'name': Joi.string().required(),
      'address': Joi.string().required(),
    });
});
