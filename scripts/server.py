import socket


if __name__ == '__main__':
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as listen_socket:
        listen_socket.bind(('localhost', 5038))

        listen_socket.listen(5)

        while True:
            client_socket, client_address = listen_socket.accept()
            while True:
                data = client_socket.recv(1024)
                if not data:
                    break
                print(data.decode())
            client_socket.close()
