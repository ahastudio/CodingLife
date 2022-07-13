/* eslint-disable import/no-extraneous-dependencies */

import net from 'net';
import http from 'http';

import express from 'express';
import cors from 'cors';

export default class TestServer {
  private id: string;

  private server?: http.Server;

  constructor() {
    this.id = Math.random().toString(16).slice(2, 10);
  }

  async start() {
    const app = express();
    app.use(cors());

    app.get('/posts', (req, res) => {
      res.send([
        { id: 1, title: 'Lesson 1', body: 'Hi' },
        { id: 2, title: 'Lesson 2', body: 'Bye' },
      ]);
    });

    const server = app.listen();
    const { port } = server.address() as net.AddressInfo;

    await (new Promise((resolve, reject) => {
      server.addListener('listening', () => {
        resolve('OK');
      });
      server.addListener('error', () => {
        reject(Error('ERROR'));
      });
    }));

    this.server = server;

    return port;
  }

  close() {
    this.server?.close();
  }
}
