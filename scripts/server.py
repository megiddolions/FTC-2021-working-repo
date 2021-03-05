#!/usr/bin/env python3

import socket
import struct
import matplotlib.pyplot as plt
import matplotlib
import numpy as np
import threading

# matplotlib.use('Agg')

def show(time_list, data_list):
    plt.cla()
    plt.plot(time_list, data_list)

    plt.pause(0.00001)

packet_size = 16

if __name__ == '__main__':

    # thread = threading.Thread(show, data_list, time_list)

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as listen_socket:
        listen_socket.bind(('', 5038))

        listen_socket.listen()

        while True:
            client_socket, client_addr = listen_socket.accept()

            data_list = [(0, 0)]
            v_list = [(0,0, 0)]
            time_list = [0]
            
            
            while True:
                # wait for all the data
                while len(client_socket.recv(packet_size, socket.MSG_PEEK)) < packet_size:
                    if not client_socket.recv(packet_size, socket.MSG_PEEK):
                        break
                
                data = client_socket.recv(packet_size)

                if not data:
                    break
                print(data)
                time = struct.unpack('l', data[:8][::-1])[0] / 1000
                left = struct.unpack('i', data[8:12][::-1])[0]
                right = struct.unpack('i', data[12:16][::-1])[0]

                v_list.append((left - data_list[-1][0], right - data_list[-1][1], (left - data_list[-1][0]+ right - data_list[-1][1])/2))

                data_list.append((left, right))
                time_list.append(time)

                show(time_list, v_list)

                if (len(data_list) >= 100): # about 10 secends
                    data_list.pop(0)
                    time_list.pop(0)
                    v_list.pop(0)    

            client_socket.close()
            break
            