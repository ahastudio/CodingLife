package com.example.demo;

import com.example.demo.presentation.RequestHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;

@Component
public class Server {
    private final HttpHandler requestHandler;

    public Server(RequestHandler requestHandler) {
        this.requestHandler = requestHandler;
    }

    public void run() throws IOException {
        InetSocketAddress address = new InetSocketAddress("localhost", 8080);
        HttpServer httpServer = HttpServer.create(address, 0);
        httpServer.createContext("/", requestHandler);
        httpServer.start();

        System.out.println("Listening on http://localhost:8080");
    }
}
