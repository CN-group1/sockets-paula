import socket

HOST = "0.0.0.0"
PORT = 12345

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_socket.bind((HOST, PORT))

print("Server UDP pornit...")

while True:
    data, addr = server_socket.recvfrom(1024)
    mesaj_client = data.decode("utf-8")
    print(f"Client {addr}: {mesaj_client}")

    if mesaj_client.lower() == "exit":
        print("Clientul a închis conversația.")
        break

    raspuns = input("Scrie răspuns pentru client: ")
    server_socket.sendto(raspuns.encode("utf-8"), addr)

    if raspuns.lower() == "exit":
        print("Serverul a închis conversația.")
        break

server_socket.close()