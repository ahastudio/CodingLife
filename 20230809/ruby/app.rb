# frozen_string_literal: true

require 'json'
require 'csv'

def main
  orders = read_orders.filter do |order|
    Time.at(order['now'] / 1000).to_s.start_with?('2023-08-06')
  end
  products = process_orders(orders)
  puts "총 주문: #{orders.size}"
  csv = generate_csv(products)
  puts csv
end

def read_orders
  filename = "#{ENV['HOME']}/Downloads/orders.txt"
  File.open(filename) do |f|
    JSON.parse("[#{f.read}{}]").reject { |i| i['now'].nil? }
  end
end

def process_orders(orders)
  products = {}

  orders.each do |order|
    order['lineItems'].each do |line_item|
      process_line_item(line_item, products)
    end
  end

  products
end

def process_line_item(line_item, products)
  product_id = line_item['product']['id']
  quantity = line_item['quantity']
  products[product_id] ||= create_product(line_item)
  product = products[product_id]
  product[:quantity] += quantity
  product[:revenue] += quantity * product[:price]
end

def create_product(line_item)
  product = line_item['product']
  {
    code: product['code'],
    category: product['category'],
    title: product['title'],
    price: product['salePrice'],
    quantity: 0,
    revenue: 0
  }
end

def generate_csv(products)
  CSV.generate do |csv|
    csv << products.first.last.keys
    products.each_value do |product|
      csv << product.values
    end
  end
end

main
