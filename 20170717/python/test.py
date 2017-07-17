import requests
from bs4 import BeautifulSoup

url = 'http://www.onoffmix.com/event?s=%EB%A8%B8%EC%8B%A0%EB%9F%AC%EB%8B%9D'
headers = { 'user-agent': 'only-for-study' }

r = requests.get(url, headers=headers)
soup = BeautifulSoup(r.text, 'html.parser')

for event in soup.find_all(class_='todayEvent'):
    if 'noneEvent' in event['class']:
        continue
    title = event.find(class_='eventTitle')
    if title.text.startswith('(AD)'):
        continue
    print(title.text)
    print(title.a['href'])
    print()
