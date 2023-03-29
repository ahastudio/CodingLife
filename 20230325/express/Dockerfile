FROM node:18.15.0

ENV NODE_ENV production

WORKDIR /u/myapp

COPY package.json .
COPY package-lock.json .

RUN npm ci
RUN npm install --global pm2

COPY . /u/myapp

CMD pm2-runtime index.js
