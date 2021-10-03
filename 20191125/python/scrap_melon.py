from pprint import pprint

import scrapy
from scrapy.crawler import CrawlerProcess


def melon_chart_url(year, month, day):
    start_date = f'{year:04}{month:02}{day:02}'
    end_date = f'{year}{month}{day + 6}'
    return 'https://www.melon.com/chart/search/list.htm?chartType=WE' \
           f'&age=2010&year={year}&mon={month}&day={start_date}%5E{end_date}' \
           f'&classCd=GN0000&startDay={start_date}&endDay={end_date}&moved=Y'


class MelonSpider(scrapy.Spider):
    name = 'melon'
    start_urls = ['https://www.melon.com/chart/index.htm']

    def parse(self, response):
        urls = [
            melon_chart_url(2019, 11, 4)
        ]
        cookies = {
            'PCID': '12345678901234567890123'
        }
        for url in urls:
            print('*' * 80)
            print(f'\n* Follow: {url}\n')
            yield response.follow(url, self.parse_chart, cookies=cookies)

    # 차트 페이지 분석
    def parse_chart(self, response):
        print('......................')
        print('......................')
        print('......................')
        print(response.url)
        print('......................')
        print('......................')
        print('......................')
        print('......................')
        # 상품 상세 페이지로 이동
        for el in response.css('tbody tr .input_check'):
            song_id = el.attrib['value']
            yield {
                'song_id': song_id,
            }
            # yield response.follow(url, self.parse_product)


if __name__ == '__main__':
    print('=' * 80)
    process = CrawlerProcess(settings={
        'FEEDS': {
            'output/chart.csv': { 'format': 'csv' },
        }
    })
    process.crawl(MelonSpider)
    process.start()
