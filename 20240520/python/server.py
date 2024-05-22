import socket


def main():
    host = "0.0.0.0"
    port = 80
    backlog = 5

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server:
        server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        server.bind((host, port))

        server.listen(backlog)

        print("Listening...")

        while True:
            (client, address) = server.accept()
            print("Accept:", address)

            data = client.recv(10_000)

            print("-" * 80)
            print(data.decode("utf-8"), end="")
            print("-" * 80)

            lines = data.decode("utf-8").split("\n")

            start_line = lines[0]

            method, url, http_version = start_line.split()

            print("Address:", address)
            print("-", http_version)
            print("- URL:", url)
            print("- Method:", method)
            print()

            message = "HTTP/1.1 200 OK\n"
            message += "Content-Type: text/html; charset=UTF-8\n"
            message += "\n"
            message += "<h1>Hello, world!</h1>\n"

            data = bytes(message, "utf-8")
            print("Message:", len(data))

            sent = client.send(data)
            print("→ Sent:", sent)

            client.close()


main()
