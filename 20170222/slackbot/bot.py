import os
import time
import konlpy
import requests
import urllib.parse
from slackclient import SlackClient


SLACK_API_TOKEN = os.environ['SLACK_API_TOKEN']

COMMANDS = {
    '가져오다': 'take',
    '필요하다': 'take',
    '알다': 'search'
}


client = SlackClient(SLACK_API_TOKEN)
twitter = konlpy.tag.Twitter()


def send_message(text):
    client.api_call('chat.postMessage', as_user=True,
                    channel='#general', text=text)


def do_command(command, targets):
    if command == 'search':
        query = urllib.parse.quote(' '.join(targets))
        send_message('https://www.google.com/search?q=%s' % query)
    else:
        send_message('%s - %s' % (command, targets))


def parse(text):
    data = {
        'message': text
    }
    try:
        response = requests.post('http://localhost:5000/requests', data=data)
        data = response.json()
    except requests.exceptions.ConnectionError:
        print('API Error')
    words = twitter.pos(text, stem=True)
    verbs = [i for i, tag in words if tag in ['Verb', 'Adjective']]
    command = None
    if len(verbs) >= 1:
        verb = verbs[-1]
        if verb in COMMANDS:
            command = COMMANDS[verb]
    if command is None:
        send_message('처리할 명령이 없습니다. - %s' % verbs)
        return
    targets = [i for i, tag in words if tag == 'Noun']
    do_command(command, targets)
    send_message('틀렸다면 http://localhost:5000/requests/%s' % data['id'])


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
                    parse(data['text'])
        time.sleep(0.1)


if __name__ == '__main__':
    main()
