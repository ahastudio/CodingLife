require 'socket'
require 'json'

names = Dir['./files/*']
  .select { |i| File.file?(i) }
  .map { |file_name| File.basename(file_name) }

server = TCPServer.new('0.0.0.0', 8080)

puts '🚀 Server started'

while session = server.accept
  puts
  puts '-' * 80

  request = session.gets
  puts request

  filename = ''
  content = ''

  names.each do |name|
    if request.start_with?("GET /#{name} ")
      filename = name
      open("./files/#{name}", "rb") do |file|
        content = file.read
      end
    end
  end

  session.print "HTTP/1.1 200\r\n"
  session.print "Content-Disposition: attachment; filename=\"#{filename}\"\r\n"
  session.print "Content-Type: application/octet-stream\r\n"
  session.print "Content-Length: #{content.size}\r\n"
  session.print "\r\n"
  session.print content

  session.close

  puts 'Completed!'
end
