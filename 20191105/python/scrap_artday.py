import scrapy
from scrapy.crawler import CrawlerProcess
from scrapy.utils.project import get_project_settings


class ArtdayAuctionSpider(scrapy.Spider):
    name = 'artday-auction'
    start_urls = [
        f'http://www.artday.co.kr/pages/auction/online-auction.php?page={i}'
        for i in range(1, 30)
    ]

    def parse(self, response):
        for item in response.css('.auction-table tbody tr'):
            image = item.css('td:nth-child(2) img::attr(src)').get()
            yield {
                'title': item.css('td:nth-child(4) .list-first::text').get(),
                'artist': item.css('td:nth-child(3) .list-first::text').get(),
                'price': item.css('td:nth-child(5) .price::text').get(),
                'image': f'http://www.artday.co.kr{image}',
            }


if __name__ == '__main__':
    process = CrawlerProcess({
        **get_project_settings(),
        'FEED_FORMAT': 'CSV',
        'FEED_URI': 'output/items.csv'
    })
    process.crawl(ArtdayAuctionSpider)
    process.start()
