import socket

HOST = "localhost"
PORT = 80

client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

client.connect((HOST, PORT))

request = "GET / HTTP/1.1\r\n"
request += "Host: localhost\r\n"
request += "Connection: close\r\n"
request += "\r\n"
request += "Hello, world!\r\n"

data = request.encode("utf-8")

client.send(data)

data = client.recv(1024)

response = data.decode("utf-8")

header, content = response.split("\r\n\r\n", maxsplit=1)
start_line, *headers = header.split("\r\n")
version, status_code, status_text = start_line.split()

print("=" * 80)
print(version, "|", status_code, status_text)
print(headers)
print("=" * 80)
print(content)
print("=" * 80)

client.close()
