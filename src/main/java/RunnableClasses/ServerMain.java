package RunnableClasses;

import java.util.Scanner;
import org.glassfish.tyrus.server.Server;

public class ServerMain {

    public static void main(String[] args) {
        String HOST = "localhost";
        int PORT = 8080;

        Server server = new Server(HOST, PORT, "/", WebSockets.WebSocketsServer.class);
        Scanner sc = new Scanner(System.in);

        try {
            server.start();
            System.out.println("Server stared on  port 8080");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
