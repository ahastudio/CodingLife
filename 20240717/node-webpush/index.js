require('dotenv').config();

const webpush = require('web-push');

const VAPID_PUBLIC_KEY = process.env.VAPID_PUBLIC_KEY;
const VAPID_PRIVATE_KEY = process.env.VAPID_PRIVATE_KEY;

webpush.setVapidDetails(
  'mailto:tester@example.com',
  VAPID_PUBLIC_KEY,
  VAPID_PRIVATE_KEY,
);

const pushSubscription = {
  endpoint: 'https://web.push.apple.com/아이폰_푸시_알림_엔드포인트',
  keys: {
    p256dh: '왱알앵알',
    auth: '뭐야?',
  },
};

const message = {
  title: '테스트',
  body: '이것은 테스트 메시지입니다.\n잘 받았나요?\n🔥🔥🔥',
};

webpush.sendNotification(pushSubscription, JSON.stringify(message));

console.log('Push sent!');
