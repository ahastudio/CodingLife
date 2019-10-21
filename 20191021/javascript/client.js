require('dotenv').config();

const SendBird = require('sendbird');

const APP_ID = process.env.SENDBIRD_APP_ID;
const USER_ID = 'tester2';
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

const main = async () => {
  const sendbird = new SendBird({ appId: APP_ID });

  await PF(sendbird, 'connect')(USER_ID);

  const channel = await PF(sendbird.OpenChannel, 'getChannel')(CHANNEL_URL);
  await PF(channel, 'enter')();

  sendbird.addChannelHandler(CHANNEL_HANDLER_ID, {
    onMessageReceived(channel, message) {
      console.log('*** RECEIVED', channel.name, message.message);
    },
    onUserEntered(channel, user) {
      console.log('*** USER ENTERED', channel.name, user.userId);
    },
    onUserExited(channel, user) {
      console.log('*** USER EXITED', channel.name, user.userId);
    },
  });

  await PF(channel, 'sendUserMessage')('Hello');

  await PF(channel, 'exit')();

  await PF(sendbird, 'disconnect')();
};

(async () => {
  try {
    await main();
  } catch (e) {
    console.error(e);
  }
})();
