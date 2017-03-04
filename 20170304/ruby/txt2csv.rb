# frozen_string_literal: true

require 'active_support/all'
require 'open-uri'
require 'csv'

# http://people.sc.fsu.edu/~jburkardt/datasets/regression/regression.html
url = 'http://people.sc.fsu.edu/~jburkardt/datasets/regression/x01.txt'
filename = 'data.csv'

lines = open(url).read.split("\n").reject { |i| i.start_with?('#') }

columns = lines.first.to_i
rows = lines.second.to_i

CSV.open(filename, 'wb') do |csv|
  csv << lines.drop(2).take(columns)
              .map { |i| i.tr(' ', '-') }. map(&:underscore)

  lines.drop(2 + columns).take(rows).each do |line|
    csv << line.split
  end
end
