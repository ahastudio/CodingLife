# frozen_string_literal: true

require 'click_house'
require 'active_support'
require 'active_support/core_ext/numeric'

ClickHouse.config do |config|
  config.host = ENV['CLICKHOUSE_HOST']
  config.port = ENV['CLICKHOUSE_PORT']
  config.database = ENV['CLICKHOUSE_DATABASE']
  config.username = ENV['CLICKHOUSE_USER']
  config.password = ENV['CLICKHOUSE_PASSWORD']
  config.ssl_verify = false
  config.symbolize_keys = true
end

def update_train_activity_channels(connection)
  result = connection.select_all("
    SELECT * FROM channels
    WHERE id BETWEEN 90000 AND 99999
  ")

  result.to_a.each do |channel|
    id = channel[:id]
    train_id = (id - 90_000) / 100
    name = "Train #{train_id} activity"
    puts id, name
    connection.execute("
      ALTER TABLE channels UPDATE name = '#{name}' WHERE id = #{id};
    ")
  end
end

def main
  ClickHouse.connection

  update_train_activity_channels(connection)
end

main
