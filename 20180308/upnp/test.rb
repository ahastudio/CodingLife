require 'easy_upnp'
require 'socket'

class App
  PORT = 8080

  def main
    device = discover_nat
    service = wan_ip_connection_service(device)
    external_ip = get_external_ip(service)
    add_port_mapping(service, PORT)
    puts '=' * 80
    puts internal_ip
    puts external_ip
    puts "http://#{external_ip}:#{PORT}/"
    run_http_server
    delete_port_mapping(service, PORT)
  end

  def discover_nat
    puts "\n<<< Discover NAT >>>"
    devices = EasyUpnp::SsdpSearcher.new.search('ssdp:all')
    puts "Found: #{devices.size}"
    device = devices.first
    puts '---'
    puts device.inspect
    puts '---'
    puts device.all_services
    device
  end

  def wan_ip_connection_service(device)
    puts "\n<<< WAN IP Connection Service >>>"
    urn = 'urn:schemas-upnp-org:service:WANIPConnection:1'
    service = device.service(urn)
    puts service.service_methods
    service
  end

  def get_external_ip(service)
    puts "\n<<< Get External IP >>>"
    result = service.GetExternalIPAddress
    puts result.inspect
    result[:NewExternalIPAddress]
  end

  def add_port_mapping(service, port)
    puts "\n<<< Add Port Mapping >>>"
    result = service.AddPortMapping(
      NewRemoteHost: '',
      NewExternalPort: port,
      NewProtocol: 'TCP',
      NewInternalPort: port,
      NewInternalClient: internal_ip,
      NewPortMappingDescription: '...'
    )
    puts result.inspect
  end

  def delete_port_mapping(service, port)
    puts "\n<<< Delete Port Mapping >>>"
    result = service.DeletePortMapping(
      NewRemoteHost: '',
      NewExternalPort: port,
      NewProtocol: 'TCP'
    )
    puts result.inspect
  end

  def internal_ip
    UDPSocket.open do |socket|
      socket.connect('64.233.187.99', 1)
      return socket.addr.last
    end
  end

  def run_http_server
    server = TCPServer.new(PORT)
    session = server.accept
    session.print "HTTP/1.1 200\r\n"
    session.print "Content-Type: text/html\r\n"
    session.print "\r\n"
    open('html/index.html') do |f|
      session.print f.read
    end
    sleep(1)
    session.close
  end
end

App.new.main
