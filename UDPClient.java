import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        String serverIP = "100.96.188.61";
        int port = 12345;

        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress address = InetAddress.getByName(serverIP);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Scrie mesaj pentru server: ");
                String mesaj = scanner.nextLine();

                byte[] sendBuffer = mesaj.getBytes("UTF-8");
                DatagramPacket sendPacket =
                        new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
                socket.send(sendPacket);

                if (mesaj.equalsIgnoreCase("exit")) {
                    System.out.println("Ai închis conversația.");
                    break;
                }

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket =
                        new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);

                String raspuns = new String(
                        receivePacket.getData(),
                        0,
                        receivePacket.getLength(),
                        "UTF-8"
                );

                System.out.println("Server: " + raspuns);

                if (raspuns.equalsIgnoreCase("exit")) {
                    System.out.println("Serverul a închis conversația.");
                    break;
                }
            }

            socket.close();
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}