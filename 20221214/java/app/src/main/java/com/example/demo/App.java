package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class App {
    public String getGreeting() {
        return "Hello, world!";
    }

    public static void main(String[] args) throws IOException {
        App app = new App();

        if (app.isServer(args)) {
            app.runServer();
            return;
        }

        app.runClient();
    }

    public boolean isServer(String[] args) {
        return args.length >= 1 && args[0].equals("server");
    }

    public void runClient() throws IOException {
        String path = "/";
        String body = "";
        String message = generateRequestMessage(path, body);

        Socket socket = new Socket("example.com", 80);

        OutputStreamWriter output =
                new OutputStreamWriter(socket.getOutputStream());

        BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        // Request

        output.write(message);
        output.flush();

        // Response

        String header = readHeader(input);
        String content = readContent(input, header);

        System.out.println("*".repeat(80));
        System.out.println(header);
        System.out.println("*".repeat(80));
        System.out.println(content);
        System.out.println("*".repeat(80));

        // Close

        socket.close();
    }

    public void runServer() throws IOException {
        int port = 3000;

        ServerSocket listener = new ServerSocket(port);

        System.out.println("=".repeat(80));
        System.out.println("Listening on " + port);

        while (true) {
            Socket socket = listener.accept();
            String address = socket.getLocalAddress().toString();
            System.out.print("\n".repeat(2));
            System.out.println("[ New Request: " + address + " ]");
            process(socket);
        }
    }

    public void process(Socket socket) throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        OutputStreamWriter output =
                new OutputStreamWriter(socket.getOutputStream());

        // Request

        String header = readHeader(input);
        String content = readContent(input, header);

        System.out.println("*".repeat(80));
        System.out.println(header);
        System.out.println("*".repeat(80));
        System.out.println(content);
        System.out.println("*".repeat(80));

        // Response

        String body = "Hello, world!";

        output.write(generateResponseMessage(body));
        output.flush();

        // Close

        socket.close();
    }

    public String generateRequestMessage(String path, String body) {
        return "" +
                "GET " + path + " HTTP/1.1\n" +
                "Host: example.com\n" +
                "User-Agent: curl/7.86.0\n" +
                "Accept: */*\n" +
                "Content-Length: 0\n" +
                "\n" +
                body;
    }

    public String generateResponseMessage(String body) {
        byte[] bytes = body.getBytes();

        return "" +
                "HTTP/1.1 200 OK\n" +
                "Content-Type: text/html; charset=UTF-8\n" +
                "Content-Length: " + bytes.length + "\n" +
                "\n" +
                body;
    }

    public String readHeader(BufferedReader reader) throws IOException {
        List<String> lines = new ArrayList<>();

        while (true) {
            String line = reader.readLine();
            if (line == null || line.length() == 0) {
                break;
            }
            lines.add(line);
        }

        return lines.stream().collect(Collectors.joining("\n"));
    }

    private String readContent(BufferedReader reader, String header)
            throws IOException {
        int contentLength = scanContentLength(header);

        if (contentLength == 0) {
            return "[ NO CONTENT ]";
        }

        CharBuffer buffer = CharBuffer.allocate(contentLength);
        int size = reader.read(buffer);

        if (size != contentLength) {
            throw new RuntimeException("Cannot read content");
        }

        buffer.flip();

        return buffer.toString();
    }

    public int scanContentLength(String header) {
        Pattern pattern = Pattern.compile("Content-Length\\:\\s+(\\d+)");
        Matcher matcher = pattern.matcher(header);
        if (!matcher.find()) {
            return 0;
        }
        return Integer.parseInt(matcher.group(1));
    }
}
