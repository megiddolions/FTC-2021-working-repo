#!/usr/bin/env python3

import socket
import struct
import matplotlib.pyplot as plt


PACKET_SIZE = 16
DATA_SIZE = 100


def show(time_list, data_list):
    # clear plot
    plt.cla()
    # plot data
    plt.plot(time_list, data_list)
    # update plot
    plt.pause(0.00001)


if __name__ == '__main__':

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as listen_socket:
        # open server on port 5038
        listen_socket.bind(('', 5038))
        # listen for connections
        listen_socket.listen()
        # for each connection
        while True:
            # accept connection
            client_socket, client_address = listen_socket.accept()
            # reset time and data buffers
            data_list = []
            time_list = []
            # for all packets from socket
            while True:
                try:
                    # get data as binary
                    data = client_socket.recv(PACKET_SIZE)
                    # if data length is 0 there is error
                    if not data:
                        break
                    # convert binary data to time and entries
                    time, v1, v2 = struct.unpack('dii'[::-1], data[::-1])
                    # add time and data to buffers
                    time_list.append(time)
                    data_list.append((v1, v2))
                    # plot data
                    show(time_list, data_list)
                    # remove old data
                    if len(time_list) == DATA_SIZE:
                        time_list.pop(0)
                        data_list.pop(0)
                # end of connection
                except ConnectionResetError:
                    break
            # close socket
            client_socket.close()
