package Middlewares;

import Models.Message;
import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

public class JsonParserDecoder implements Decoder.Text<Message> {

    @Override
    public void init(final EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }

    @Override
    public Message decode(final String textMessage) throws DecodeException {
        Message message = new Message();
        JsonObject json = Json.createReader(new StringReader(textMessage)).readObject();
        message.setMessage(json.getString("message"));
        message.setAuthor(json.getString("author"));
        message.setTo(json.getString("to"));
        return message;
    }

    @Override
    public boolean willDecode(final String s) {
        return true;
    }

}
