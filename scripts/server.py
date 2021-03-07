import socket


if __name__ == '__main__':
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as listen_socket:
        listen_socket.bind(('127.0.0.1', 5039))

        listen_socket.listen(5)

        while True:
            client_socket, client_address = listen_socket.accept()
            try:
                # print(client_address)
                while True:
                    data = client_socket.recv(1024)
                    if not data:
                        break
                    print(data.decode())
            except Exception:
                pass
            client_socket.close()
