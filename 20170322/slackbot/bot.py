import os
import time
import konlpy
import requests
import numpy as np
from slackclient import SlackClient


SLACK_API_TOKEN = os.environ['SLACK_API_TOKEN']

client = SlackClient(SLACK_API_TOKEN)
twitter = konlpy.tag.Twitter()

foods = [
    '치킨',
    '자장면',
    '피자',
    '떡볶이',
    '족발',
    '보쌈',
    '돈까스',
    '회',
    '우동',
    '아구찜',
    '매운탕',
    '햄버거'
]

triggers = [
    '배고프다',
    '뭐 먹다',
    '뭘 먹다',
    '뭐 먹을까'
]

yes_words = [
    '괜찮다',
    '그렇다',
    'ㅇㅇ'
]

no_words = [
    '다른 거',
    '다른 거 없다',
    '싫다',
    '아니다'
]

recommended_food = None


def send_message(text):
    client.api_call('chat.postMessage', as_user=True,
                    channel='#general', text=text)


def recommend_food():
    global recommended_food
    p = np.repeat(1.0, len(foods))
    if recommended_food in foods:
        p[foods.index(recommended_food)] = 0.0
    p /= p.sum()
    recommended_food = np.random.choice(foods, 1, p=p)[0]
    send_message('{food} 어때?'.format(food=recommended_food))


def parse(text):
    words = twitter.pos(text, stem=True)
    print(words)
    talk = ' '.join([x[0] for x in words
                    if x[1] in ['Noun', 'Verb', 'Adjective']])
    print(talk)
    if talk in triggers:
        recommend_food()
    elif recommended_food:
        if any(x[0] in yes_words for x in words):
            send_message('넌 {food}을/를 좋아하는구나?'.format(
                         food=recommended_food))
        elif any(x[0] in no_words for x in words):
            recommend_food()


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
