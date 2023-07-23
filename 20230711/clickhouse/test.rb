require 'securerandom'

(1..100_000).each do |index|
  puts index

  sql = 'INSERT INTO test_items (user_id, message, timestamp, metric) VALUES'

  sql << (0...10_000).map { |i|
    " (#{i}, 'Hello, #{SecureRandom.hex}!', now() - #{i * index}, 3.14)"
  }.join(',')

  # puts sql

  system("http localhost:8123 <<< \"#{sql}\" > /dev/null")
end
