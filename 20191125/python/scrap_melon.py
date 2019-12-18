import scrapy
from scrapy.crawler import CrawlerProcess
from scrapy.utils.project import get_project_settings

DEFAULT_REQUEST_HEADERS = {
    'Referer': 'https://www.melon.com/chart/search/index.htm',
}


def melon_chart_url(year, month, day):
    start_date = f'{year:04}{month:02}{day:02}'
    end_date = f'{year}{month}{day + 6}'
    return 'https://www.melon.com/chart/search/list.htm?chartType=WE' \
           f'&age=2010&year={year}&mon={month}&day={start_date}%5E{end_date}' \
           f'&classCd=GN0000&startDay={start_date}&endDay={end_date}&moved=Y'


class MelonSpider(scrapy.Spider):
    name = 'melon'

    custom_settings = {
        'USER_AGENT': 'Mozilla/5.0 (Windows NT 6.3; Trident/7.0; rv:11.0)'
                      ' like Gecko',
    }

    start_urls = [
        melon_chart_url(2019, 11, 4),
    ]

    # 차트 페이지 분석
    def parse(self, response):
        print('......................')
        print('......................')
        print(response.url)
        print('......................')
        print('......................')
        print('......................')
        print('......................')
        # 상품 상세 페이지로 이동
        for el in response.css('tr .btn_icon.play ::text'):
            song_id = el.get()
            yield {
                song_id: song_id,
            }
            # yield response.follow(url, self.parse_product)


if __name__ == '__main__':
    process = CrawlerProcess({
        **get_project_settings(),
        'FEED_FORMAT': 'CSV',
        'FEED_URI': 'output/chart.csv'
    })
    process.crawl(MelonSpider)
    process.start()
