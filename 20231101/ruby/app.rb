# frozen_string_literal: true

require 'csv'
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

def insert_dcs_channels(connection)
  rows = CSV.foreach('data/dcs_channels.csv', headers: true)

  rows.each_slice(10) do |chunk_rows|
    query = 'INSERT INTO channels (id, value_type, name, description) VALUES '

    chunk_rows.each do |row|
      id = row['id'].gsub(',', '').to_i
      value_type = row['value_type']
      name = row['name']
      description = row['description'].gsub("'", "\\'")

      puts [id, value_type, name, description].inspect

      query += " (#{id}, '#{value_type}', '#{name}', '#{description}')"
    end

    connection.execute(query)
  end
end

def insert_dcs_additional_channels(connection, key, offset)
  timestamp = '2020-01-01 00:00:00'

  rows = CSV.foreach('data/dcs_channels.csv', headers: true)

  rows.each do |row|
    value = row[key].to_s.gsub(/[,X]/, '').strip
    next if value.blank? or value == '-'

    id = row['id'].gsub(',', '').to_i
    name = "#{key} of #{row['name']}"
    value = value.to_f

    id += offset

    puts [id, name, value].inspect

    connection.execute("
      INSERT INTO channels (id, value_type, name)
      VALUES (#{id}, 'float', '#{name}');
    ")

    connection.execute("
      INSERT INTO float_signals (channel_id, value, timestamp)
      VALUES (#{id}, #{value}, '#{timestamp}');
    ")
  end
end

def insert_dcs_status_channels(connection)
  timestamp = '2020-01-01 00:00:00'

  rows = CSV.foreach('data/dcs_channels.csv', headers: true)

  rows.each do |row|
    id = row['id'].gsub(',', '').to_i + 80_000
    name = "status of #{row['name']}"

    puts [id, name].inspect

    connection.execute("
      INSERT INTO channels (id, value_type, name)
      VALUES (#{id}, 'symbol', '#{name}');
    ")
  end
end

def insert_bearing_channels(connection)
  rows = CSV.foreach('data/vms_channels.csv', headers: true)
  ids = CSV.foreach('data/bearing_ids.csv').to_a.flatten.map(&:to_i)

  ids.each_slice(20) do |item_ids|
    query = 'INSERT INTO channels (id, value_type, name) VALUES '

    item_ids.each do |item_id|
      prefix = "Bearing #{item_id}"

      rows.each do |row|
        id = row['id'].to_i - 100 + (item_id * 100)
        name = "#{prefix} #{row['name']}"

        puts [id, name].inspect

        query += " (#{id}, '#{row['value_type']}', '#{name}')"
      end
    end

    connection.execute(query)
  end
end

def insert_bearings_rated_speed(connection)
  timestamp = '2020-01-01 00:00:00'

  rows = CSV.foreach('data/bearings.csv', headers: true)

  rows.each_slice(10) do |items|
    query = 'INSERT INTO float_signals (channel_id, value, timestamp) VALUES '

    items.each do |row|
      bearing_id = row['id'].to_i
      channel_id = 100_000 + bearing_id * 100 + 70
      rated_speed = row['rated_speed'].to_f

      puts [bearing_id, rated_speed].inspect

      query += " (#{channel_id}, 0, '#{timestamp}')"
      query += " (#{channel_id + 1}, #{rated_speed * 1.3}, '#{timestamp}')"
      query += " (#{channel_id + 2}, #{rated_speed}, '#{timestamp}')"
    end

    connection.execute(query)
  end
end

def insert_bearings_sync_rpm(connection, train_ids:)
  timestamp = '2020-01-01 00:00:00'

  rows = CSV.foreach('data/bearings.csv', headers: true)

  rows.each_slice(10) do |items|
    query = 'INSERT INTO float_signals (channel_id, value, timestamp) VALUES '

    items.each do |row|
      train_id = row['train_id'].to_i

      next unless train_ids.include?(train_id)

      bearing_id = row['id'].to_i
      channel_id = 100_000 + bearing_id * 100
      rpm = row['rated_speed'].to_f

      puts [bearing_id, rpm].inspect

      query += " (#{channel_id}, #{rpm}, '#{timestamp}')"
    end

    # connection.execute(query)
  end
end

def insert_bearing_signals(connection, value_type:, value:)
  table = "#{value_type}_signals"
  timestamp = '2020-01-01 00:00:00'

  rows = CSV.foreach('data/vms_channels.csv', headers: true)
  ids = CSV.foreach('data/bearing_ids.csv').to_a.flatten.map(&:to_i)

  ids.each_slice(20) do |item_ids|
    query = "INSERT INTO #{table} (channel_id, value, timestamp) VALUES "

    item_ids.each do |item_id|
      rows.each do |row|
        next if row['value_type'] != value_type

        channel_id = row['id'].to_i - 100 + (item_id * 100)

        puts channel_id

        query += " (#{channel_id}, '#{value}', '#{timestamp}')"
      end
    end

    connection.execute(query)
  end
end

def insert_sharft_center_offsets(connection)
  table = 'coordinate_signals'
  value = '(-10.35, -10.67)'
  timestamp = '2020-01-01 00:00:00'

  ids = CSV.foreach('data/bearing_ids.csv').to_a.flatten.map(&:to_i)

  ids.each_slice(20) do |beraing_ids|
    query = "INSERT INTO #{table} (channel_id, value, timestamp) VALUES "

    beraing_ids.each do |beraing_id|
      channel_id = 200_000 + (beraing_id * 100) + 50

      puts channel_id

      query += " (#{channel_id}, #{value}, '#{timestamp}')"
    end

    connection.execute(query)
  end
end

def main
  connection = ClickHouse.connection

  # update_train_activity_channels(connection)
  # insert_dcs_channels(connection)
  # insert_dcs_additional_channels(connection, 'min', 1_0000)
  # insert_dcs_additional_channels(connection, 'max', 2_0000)
  # insert_dcs_additional_channels(connection, 'low_trip', 4_0000)
  # insert_dcs_additional_channels(connection, 'high_trip', 5_0000)
  # insert_dcs_additional_channels(connection, 'low_alarm', 6_0000)
  # insert_dcs_additional_channels(connection, 'high_alarm', 7_0000)
  # insert_dcs_status_channels(connection)
  # insert_bearing_channels(connection)
  # insert_bearings_rated_speed(connection)
  # insert_bearings_sync_rpm(connection, train_ids: [3, 4, 5, 6])
  # insert_bearing_signals(connection, value_type: 'float', value: 0)
  # insert_bearing_signals(connection, value_type: 'symbol', value: 'normal')
  # insert_sharft_center_offsets(connection)
end

main
