import scrapy
from scrapy.crawler import CrawlerProcess
from scrapy.utils.project import get_project_settings


class CatpangSpider(scrapy.Spider):
    name = 'catpang'
    start_urls = [
        'http://www.catpang.com/shop/goods/goods_list.php?category=002001'
    ]

    # 상품 목록 페이지 분석
    def parse(self, response):
        # 상품 목록의 다른 페이지로 이동
        for link in response.css('.pager a::attr(href)'):
            yield response.follow(link.get(), self.parse)
        # 상품 상세 페이지로 이동
        for link in response.css('.item-info a::attr(href)'):
            url = link.get().split('#')[0]
            yield response.follow(url, self.parse_product)

    # 상품 상세 페이지 분석
    def parse_product(self, response):
        yield {
            'url': response.url,
            'name': response.css('#viewName::text').get(),
            'price': response.css('.price-sell .num::text').get(),
            'image': response.css('.photo-view img::attr(src)').get(),
        }


if __name__ == '__main__':
    process = CrawlerProcess({
        **get_project_settings(),
        'FEED_FORMAT': 'CSV',
        'FEED_URI': 'output/products.csv'
    })
    process.crawl(CatpangSpider)
    process.start()
