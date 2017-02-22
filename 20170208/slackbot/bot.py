import os
import time
from slackclient import SlackClient


SLACK_API_TOKEN = os.environ['SLACK_API_TOKEN']

client = SlackClient(SLACK_API_TOKEN)


def send_message(text):
    client.api_call('chat.postMessage', as_user=True,
                    channel='#general', text=text)

def main():
    connect = client.rtm_connect()
    if not connect:
        print('Slack RTM Connect Error!')
        return
    print('Slack RTM Connect Success!')
    while True:
        for data in client.rtm_read():
            if data['type'] == 'message':
                if 'bot_id' not in data:
                    send_message('따라한다! ' + data['text'])
        time.sleep(0.1)


if __name__ == '__main__':
    main()
