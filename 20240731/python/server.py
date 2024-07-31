import socket

HOST = "0.0.0.0"
PORT = 80

with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
    server.bind((HOST, PORT))
    server.listen()

    print("Listening for connections...")

    while True:
        client, address = server.accept()
        print(address)

        data = client.recv(1024)
        request = data.decode("utf-8")

        header, content = request.split("\r\n\r\n", maxsplit=1)
        start_line, *headers = header.split("\r\n")
        method, path, version = start_line.split()

        print("=" * 80)
        print(method, path, version)
        print(headers)
        print("=" * 80)
        print(content)
        print("=" * 80)

        if path == "/":
            content = "<h1>Welcome!</h1>"
            content += "<p>Hello, world!</p>\r\n"
            data = content.encode("utf-8")
            response = "HTTP/1.1 200 OK\r\n"
        elif path.startswith("/hello/"):
            name = path[len("/hello/") :]
            content = "<h1>Greeting</h1>\r\n"
            content += f"<p>Hello, {name}!</p>\r\n"
            data = content.encode("utf-8")
            response = "HTTP/1.1 200 OK\r\n"
        else:
            content = "<h1>404 Not Found</h1>\r\n"
            content += f"<p>URL {path} was not found on this server.</p>\r\n"
            data = content.encode("utf-8")
            response = "HTTP/1.1 404 Not Found\r\n"

        response += "Content-Type: text/html\r\n"
        response += f"Content-Length: {len(data)}\r\n"
        response += "\r\n"
        response += content

        data = response.encode("utf-8")
        client.send(data)

        client.close()
