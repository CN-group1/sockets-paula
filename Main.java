import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String serverIP = "100.96.188.61";
        int port = 12345;

        try {
            Socket socket = new Socket(serverIP, port);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Scrie mesaj pentru server: ");
                String mesaj = scanner.nextLine();

                out.write(mesaj.getBytes("UTF-8"));

                if (mesaj.equalsIgnoreCase("exit")) {
                    System.out.println("Ai închis conversația.");
                    break;
                }

                byte[] buffer = new byte[1024];
                int bytesRead = in.read(buffer);

                if (bytesRead == -1) {
                    System.out.println("Serverul a închis conexiunea.");
                    break;
                }

                String raspuns = new String(buffer, 0, bytesRead, "UTF-8");
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