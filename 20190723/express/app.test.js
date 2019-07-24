const request = require('supertest');

const app = require('./app');

describe('app', () => {
  describe('GET /tasks', () => {
    it('responses 200', done => {
      request(app)
        .get('/tasks')
        .expect('Content-Type', /json/)
        .expect(200, done);
    });
  });

  describe('POST /tasks', () => {
    describe('with valid attributes', () => {
      it('responses 201', done => {
        request(app)
          .post('/tasks')
          .send({ title: 'Test Task' })
          .set('Accept', 'application/json')
          .expect(201, done);
      });
    });

    describe('with invalid attributes', () => {
      it('responses 400', done => {
        request(app)
          .post('/tasks')
          .send({ title: '' })
          .set('Accept', 'application/json')
          .expect(400, done);
      });
    });
  });
});
