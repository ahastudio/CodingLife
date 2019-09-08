import os
import requests


key = os.environ['KAKAO_REST_API_KEY']


def geocode(address):
    url = 'https://dapi.kakao.com/v2/local/search/address.json'
    params = {'query': address}
    headers = {'Authorization': 'KakaoAK ' + key}
    r = requests.get(url, params=params, headers=headers)
    results = r.json()['documents']
    if not results:
        return None, None
    address = results[0]['address']
    return float(address['y']), float(address['x'])


if __name__ == '__main__':
    lat, lng = geocode('서울특별시 마포구 백범로31길 21 창업허브')
    assert lat == 37.54674671893906
    assert lng == 126.94997413872638
