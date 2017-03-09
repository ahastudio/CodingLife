import os
import time
import konlpy
import requests
import numpy as np
from slackclient import SlackClient


SLACK_API_TOKEN = os.environ['SLACK_API_TOKEN']

client = SlackClient(SLACK_API_TOKEN)
twitter = konlpy.tag.Twitter()


def send_message(text):
    client.api_call('chat.postMessage', as_user=True,
                    channel='#general', text=text)


def create_request(data):
    try:
        response = requests.post('http://localhost:5000/requests',
                                 data=data)
        data = response.json()
        return 'http://localhost:5000/requests/%s' % data['id']
    except requests.exceptions.ConnectionError:
        print('API Error')


def do_command_search(data, targets):
    keyword = ' '.join(targets)
    data['keyword'] = keyword
    url = create_request(data)
    send_message('“{keyword}” 구글 검색 결과입니다: {url}'.format(
        keyword=keyword, url=url
    ))


def do_command_buy_icecream(data, targets):
    keyword = '아이스크림'
    data['keyword'] = keyword
    data['url'] = 'http://www.baskinrobbins.co.kr/'
    url = create_request(data)
    send_message('“{keyword}”을 원하시나요? {url}'.format(
        keyword=keyword, url=url
    ))


def do_command_search_hospital(data, targets):
    do_command_search(data, ['병원'])


def do_command_search_fun(data, targets):
    items = ['픽미업', '리니지', '고수']
    probabilities = [7, 4, 3]
    target = np.random.choice(items, p=probabilities / np.sum(probabilities))
    do_command_search(data, [target])


COMMANDS = {
    '알다': do_command_search,
    '먹다/싶다': do_command_search,
    '덥다': do_command_buy_icecream,
    '아프다': do_command_search_hospital,
    '심심하다': do_command_search_fun,
    '졸리다': do_command_search_fun
}


def parse(text):
    words = twitter.pos(text, stem=True)
    verbs = [i for i, tag in words if tag in ['Verb', 'Adjective']]
    command = None
    if len(verbs) >= 1:
        verb = '/'.join(verbs)
        if verb in COMMANDS:
            command = COMMANDS[verb]
    targets = [i for i, tag in words if tag == 'Noun']
    data = {'message': text}
    if command:
        command(data, targets)
    else:
        send_message('처리할 명령이 없습니다. - %s' % verbs)


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
