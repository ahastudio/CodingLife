package com.ahastudio.http.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.Socket;
import java.nio.CharBuffer;

public class App {
    public static void main(String[] args) throws IOException {
        App app = new App();
        app.run();
    }

    private void run() throws IOException {
        System.out.println("Hello, world!");

        // 1. Connect

        try (Socket socket = new Socket("example.com", 80)) {
            System.out.println("Connect!");

            // 2. Request

            String message = """
                    GET / HTTP/1.1
                    Host: example.com

                    """;

            OutputStream outputStream = socket.getOutputStream();
            Writer writer = new OutputStreamWriter(outputStream);

            writer.write(message);
            writer.flush();

            System.out.println("Request!");

            // 3. Response

            InputStream inputStream = socket.getInputStream();
            Reader reader = new InputStreamReader(inputStream);

            CharBuffer charBuffer = CharBuffer.allocate(1_000_000);

            reader.read(charBuffer);

            charBuffer.flip();

            String text = charBuffer.toString();

            System.out.println(text);
        }

        // 4. Close

        System.out.println("Complete!");
    }
}
