import socket

HOST = "0.0.0.0"
PORT = 12345

server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind((HOST, PORT))
server_socket.listen(1)

print("Server TCP pornit...")

client_socket, addr = server_socket.accept()
print(f"Conectat cu {addr}")

while True:
    data = client_socket.recv(1024)
    if not data:
        break

    mesaj_client = data.decode("utf-8")
    print("Client:", mesaj_client)

    if mesaj_client.lower() == "exit":
        print("Clientul a închis conversația.")
        break

    raspuns = input("Scrie răspuns pentru client: ")
    client_socket.send(raspuns.encode("utf-8"))

    if raspuns.lower() == "exit":
        print("Serverul a închis conversația.")
        break

client_socket.close()
server_socket.close()