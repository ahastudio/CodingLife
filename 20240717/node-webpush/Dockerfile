FROM node:20.15.1-alpine3.20

ENV NODE_ENV production

WORKDIR /u/myapp

COPY fetch.js .

ENTRYPOINT ["node", "fetch.js"]
