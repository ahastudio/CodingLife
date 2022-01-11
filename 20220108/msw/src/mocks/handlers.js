import { rest } from 'msw';

const BASE_URL = 'https://example.com';

let newId = 10;

const posts = [
  { id: 1, title: '1st post', body: 'Hello!' },
];

export const handlers = [
  rest.get(`${BASE_URL}/posts`, (req, res, ctx) => {
    return res(
      ctx.status(200),
      ctx.json({
        posts,
      }),
    );
  }),

  rest.post(`${BASE_URL}/posts`, (req, res, ctx) => {
    newId += 1;

    const post = {
      id: newId,
      title: `Post-${newId}`,
      body: 'Hello...',
    };

    posts.push(post);

    return res(
      ctx.status(201),
      ctx.json({
        post,
      }),
    );
  }),
];
