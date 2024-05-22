import socket
import textwrap


def main():
    host = "example.com"
    # host = "localhost"

    # SOCK_STREAM = TCP
    # SOCK_DGRAM = UDP
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client:
        print("Connect...")
        client.connect((host, 80))
        print("Connected!")

        # https://developer.mozilla.org/ko/docs/Web/HTTP/Messages
        message = textwrap.dedent(
            f"""
            GET / HTTP/1.1
            Host: {host}

            """
        ).lstrip()

        data = bytes(message, "utf-8")
        print("Message:", len(data))

        sent = client.send(data)
        print("→ Sent:", sent)

        print("-" * 80)

        # TODO: loop!
        if True:
            data = client.recv(10_000)
            print(data.decode("utf-8"), end="")

        print("-" * 80)

    print("Closed!")


main()
