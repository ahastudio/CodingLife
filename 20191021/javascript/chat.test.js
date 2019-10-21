require('dotenv').config();

jest.setTimeout(30 * 1000);

const waitForExpect = require('wait-for-expect');

waitForExpect.defaults.timeout = 10 * 1000;

const { promisify } = require('util');
const { exec } = require('child_process');

const SendBird = require('sendbird');

const APP_ID = process.env.SENDBIRD_APP_ID;
const USER_ID = process.env.SENDBIRD_USER_ID;
const CHANNEL_URL = process.env.SENDBIRD_CHANNEL_URL;
const CHANNEL_HANDLER_ID = 'channel-handler';

const PF = (c, fn) => (...arg) => new Promise((resolve, reject) => {
  c[fn].call(c, ...arg, (result, error) => {
    if (error) {
      reject(error);
      return;
    }
    resolve(result);
  });
});

test('SendBird', async () => {
  let log = '';

  const sendbird = new SendBird({ appId: APP_ID });

  await PF(sendbird, 'connect')(USER_ID);

  const channel = await PF(sendbird.OpenChannel, 'getChannel')(CHANNEL_URL);
  await PF(channel, 'enter')();

  sendbird.addChannelHandler(CHANNEL_HANDLER_ID, {
    onMessageReceived(channel, message) {
      log += `message-received:“${message.message}”.`;
    },
    onUserEntered(channel, user) {
      if (user.userId === USER_ID) {
        return;
      }
      log += 'user-entered.';
    },
    onUserExited(channel, user) {
      if (user.userId === USER_ID) {
        return;
      }
      log += 'user-exited.';
    },
  });

  await promisify(exec)('node client.js');

  await waitForExpect(() => {
    expect(log).toBe('user-entered.message-received:“Hello”.user-exited.');
  });

  await PF(channel, 'exit')();

  sendbird.removeChannelHandler(CHANNEL_HANDLER_ID);

  await PF(sendbird, 'disconnect')();
});
