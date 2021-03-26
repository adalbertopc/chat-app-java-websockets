package WebSockets;

import Models.Message;
import Middlewares.JsonParserDecoder;
import Middlewares.JsonParserEncoder;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;

// encoder y decoder y ruta del endpoint del websocket con su parametro para el usuario.
@javax.websocket.server.ServerEndpoint(value = "/{key}", encoders = JsonParserEncoder.class, decoders = JsonParserDecoder.class)
public class WebSocketsServer {

    static Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("key") String key) {
        System.out.println("User " + key + "Log in");
        sessions.put(key, session);
    }

    //aqui llegan los mensajes de los clientes con le objeto message
    @OnMessage
    public void onMessage(Message message, Session session) throws IOException, EncodeException {
        String user = (String) session.getUserProperties().get("user");
        System.out.println(user);
        if (user == null) {
            session.getUserProperties().put("user", message.getAuthor());
        }

        System.out.println("Message: " + message.getMessage());
        System.out.println("Author: " + message.getAuthor());
        System.out.println("To: " + message.getTo());
        
        if (message.getTo().equals("all")) {
            for (Map.Entry<String, Session> entry : sessions.entrySet()) {
                //System.out.println(entry.getKey() + " / " + entry.getValue());
                if (!session.getId().equals(entry.getValue().getId())) {
                    entry.getValue().getBasicRemote().sendObject(message);
                }
            }
        } else {
            sessions.get(message.getTo()).getBasicRemote().sendObject(message);
        }
//        System.out.println(sessions.get(message.getTo()).getBasicRemote());

    }

    @OnClose
    public void onClose(Session session) throws IOException, EncodeException {
        sessions.remove(session);
    }

}
