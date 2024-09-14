package com.example;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class App {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }

    public void run() throws IOException {
        MyHttpHandler handler = new MyHttpHandler();

        InetSocketAddress address = new InetSocketAddress(HOST, PORT);
        HttpServer httpServer = HttpServer.create(address, 0);
        httpServer.createContext("/", handler);
        httpServer.start();

        System.out.println("Listening on http://" + HOST + ":" + PORT);
    }
}
