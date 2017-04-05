import os
import csv
import time
import konlpy
import requests
import numpy as np
from korean import Noun
from slackclient import SlackClient
from restaurant import Restaurant


SLACK_API_TOKEN = os.environ['SLACK_API_TOKEN']

client = SlackClient(SLACK_API_TOKEN)
twitter = konlpy.tag.Twitter()

restaurants = []

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

recommended_restaurant = None


def send_message(text):
    client.api_call('chat.postMessage', as_user=True,
                    channel='#general', text=text)


def recommend_food():
    global recommended_restaurant
    p = np.repeat(1.0, len(restaurants))
    if recommended_restaurant in restaurants:
        p[restaurants.index(recommended_restaurant)] = 0.0
    p /= p.sum()
    recommended_restaurant = np.random.choice(restaurants, 1, p=p)[0]
    send_message('{food:은} 어때?'
                 .format(food=Noun(recommended_restaurant.food)))


def send_restaurant_info(restaurant):
    send_message('그럼 {name:은} 어때? {kind:을} 먹을 수 있는 곳이야\n'
                 '주소: {address}\n전화번호: {phone}'
                 .format(name=Noun(restaurant.name),
                         kind=Noun(restaurant.kind),
                         address=Noun(restaurant.address),
                         phone=Noun(restaurant.phone)))


def parse(text):
    words = twitter.pos(text, stem=True)
    print(words)
    talk = ' '.join([x[0] for x in words
                    if x[1] in ['Noun', 'Verb', 'Adjective']])
    print(talk)
    if talk in triggers:
        recommend_food()
    elif recommended_restaurant:
        if any(x[0] in yes_words for x in words):
            send_restaurant_info(recommended_restaurant)
        elif any(x[0] in no_words for x in words):
            recommend_food()


def load_restaurant_data():
    table = {
        '업소명': 'name', '업태명': 'kind', '소재지(지번)': 'address',
        '소재지전화번호': 'phone', '주취급음식': 'food'
    }
    # 데이터 출처: https://www.data.go.kr/dataset/3053840/fileData.do
    with open('restaurant.csv', encoding='euc-kr') as f:
        reader = csv.DictReader(f)
        for row in reader:
            data = {table[x]: row[x] for x in table.keys()}
            restaurant = Restaurant(**data)
            restaurants.append(restaurant)
            print(restaurant)
    print('--- loading complete ---')


def main():
    load_restaurant_data()
    connect = client.rtm_connect()
    if not connect:
        print('Slack RTM Connect Error!')
        return
    print('Slack RTM Connect Success!')
    while True:
        for data in client.rtm_read():
            print('---------------')
            print(data)
            print('---------------')
            if data['type'] == 'message':
                if 'bot_id' not in data:
                    parse(data['text'])
        time.sleep(0.1)


if __name__ == '__main__':
    main()
