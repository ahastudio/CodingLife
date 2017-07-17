require 'net/http'
require 'nokogiri'

url = 'http://www.onoffmix.com/event?s=%EB%A8%B8%EC%8B%A0%EB%9F%AC%EB%8B%9D'
uri = URI(url)

request = Net::HTTP::Get.new(uri)
request['User-Agent'] = 'only-for-study'

Net::HTTP.start(uri.host, uri.port) do |http|
  response = http.request(request)
  doc = Nokogiri::HTML(response.body)
  doc.css('.contentBox > ul').each_with_index do |item, index|
    next if item.css('.eventTitle').empty?
    title = item.css('.eventTitle a').text.strip
    puts "#{index + 1}. #{title}"
  end
end

