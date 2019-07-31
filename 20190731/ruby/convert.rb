require 'csv'
require 'uri'
require 'net/http'

class App
  CSV_URL = 'https://docs.google.com/spreadsheets/d/e/2PACX-1vTS0_nmh7fDHpx-xe9fe2V-XUd68xxb2w5EGtFinZmsXaCT7Jih7pOtQAAi_QQtt8tqKdOK2RPzuRfp/pub?gid=327128566&single=true&output=csv'

  def main
    table = CSV.parse(data, headers: true, nil_value: 0)
    table.delete_if { |row| row['Index'].to_i.zero? }
    display(table)
    File.open('data.csv', 'w') do |f|
      f.write(table)
    end
  end

  def display(rows)
    days = rows.headers[1..-1]
    days.each do |day|
      puts day
      puts rows.map { |i| i[day] }.compact.join(',')
      puts
    end
  end

  def data
    @data ||= Net::HTTP.get(URI.parse(CSV_URL))
  end
end

App.new.main
