package com.example.demo.presentation;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class RequestHandler implements HttpHandler {
    private final Map<String, ResourceMethodHandler> handlers = new HashMap<>();

    public RequestHandler(
            HomeGetHandler homeGetResource,
            CalculationCreateHandler calculationCreateResource,
            CalculationListHandler calculationListResource
    ) {
        addResourceMethodHandler(homeGetResource);
        addResourceMethodHandler(calculationCreateResource);
        addResourceMethodHandler(calculationListResource);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestKey = getRequestKey(exchange);
        System.out.println(requestKey);

        if (!handlers.containsKey(requestKey)) {
            exchange.sendResponseHeaders(404, -1);
            return;
        }

        String requestContent = getRequestContent(exchange);

        ResourceMethodHandler handler = handlers.get(requestKey);
        String responseContent = handler.handle(requestContent);

        sendResponseContent(exchange, responseContent);
    }

    private void addResourceMethodHandler(ResourceMethodHandler handler) {
        handlers.put(handler.key(), handler);
    }

    private String getRequestKey(HttpExchange exchange) {
        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        String path = uri.getPath();

        return method + " " + path;
    }

    private String getRequestContent(HttpExchange exchange) throws IOException {
        InputStream inputStream = exchange.getRequestBody();
        return new String(inputStream.readAllBytes());
    }

    private void sendResponseContent(
            HttpExchange exchange, String responseContent) throws IOException {
        byte[] bytes = responseContent.getBytes();

        exchange.sendResponseHeaders(200, bytes.length);

        try (OutputStream outputStream = exchange.getResponseBody()) {
            outputStream.write(bytes);
        }
    }
}
