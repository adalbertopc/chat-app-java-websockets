package Client;

import Models.Message;
import Middlewares.JsonParserDecoder;
import Middlewares.JsonParserEncoder;
import javax.websocket.OnMessage;

@javax.websocket.ClientEndpoint(encoders = JsonParserEncoder.class, decoders = JsonParserDecoder.class)
public class Client {

    @OnMessage
    public void onMessage(Message message) {
        System.out.println("\nMensaje recibido: " + message.getMessage());
        System.out.println("Author: " + message.getAuthor());
        System.out.println("To: " + message.getTo());
    }

}
