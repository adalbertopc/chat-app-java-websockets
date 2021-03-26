package RunnableClasses;

import Client.Client;
import java.net.URI;
import java.util.Scanner;
import javax.json.Json;
import javax.websocket.Session;
import org.glassfish.tyrus.client.ClientManager;

public class ClientMain {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            ClientManager client = ClientManager.createClient();
            String message;

            System.out.println("Username: ");
            String user = scanner.nextLine();
            String SERVER = "ws://localhost:8080/" + user;
            System.out.println("To: ");
            String to = scanner.nextLine();
            
            Session session = client.connectToServer(Client.class, new URI(SERVER));
           
            do {
                System.out.println("Send a message:");
                message = scanner.nextLine();
                
                String json = Json.createObjectBuilder()
                        .add("message", message)
                        .add("author", user)
                        .add("to", to)
                        .build().toString();
                session.getBasicRemote().sendText(json);
            } while (!message.equalsIgnoreCase("exit"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
