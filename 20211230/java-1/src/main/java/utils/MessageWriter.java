package utils;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class MessageWriter {
    private HttpExchange exchange;

    public MessageWriter(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public void write(String content) throws IOException {
        exchange.sendResponseHeaders(200, content.getBytes().length);

        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(content.getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
