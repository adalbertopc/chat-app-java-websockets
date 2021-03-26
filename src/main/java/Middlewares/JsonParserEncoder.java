package Middlewares;

import Models.Message;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.json.Json;

public class JsonParserEncoder implements Encoder.Text<Message> {

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public String encode(final Message message) throws EncodeException {
        return Json.createObjectBuilder()
                .add("message", message.getMessage())
                .add("author", message.getAuthor())
                .add("to", message.getTo())
                .build()
                .toString();
    }

}
