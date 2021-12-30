package utils;

import java.io.InputStream;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;

public class RequestBodyReader {
    private final HttpExchange exchange;

    public RequestBodyReader(HttpExchange exchange) {
        this.exchange = exchange;
    }

    public String body() {
        InputStream inputStream = exchange.getRequestBody();
        Scanner scanner = new Scanner(inputStream);

        if (!scanner.hasNextLine()) {
            return "";
        }

        return scanner.nextLine();
    }
}
