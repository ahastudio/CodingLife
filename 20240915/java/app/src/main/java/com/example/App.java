package com.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    private static final String HOSTNAME = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }

    private void run() throws IOException {
        MyHttpHandler httpHandler = new MyHttpHandler();

        InetSocketAddress addrees = new InetSocketAddress(HOSTNAME, PORT);
        HttpServer httpServer = HttpServer.create(addrees, 0);
        httpServer.createContext("/", httpHandler);
        httpServer.start();

        System.out.println("Listening on http://" + HOSTNAME + ":" + PORT);
    }
}
