package com.ahastudio.todo.infrastructure;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.ahastudio.todo.handlers.Handler;
import com.ahastudio.todo.views.View;

public class HttpServer {
    private static final int PORT = 8080;

    protected final Map<Pair<String, String>, Handler>
            handlers = new HashMap<>();

    protected void run() throws IOException {
        ServerSocket listener = new ServerSocket(PORT, 0);

        while (true) {
            Socket socket = listener.accept();
            process(socket);
            socket.close();
        }
    }

    private void process(Socket socket) throws IOException {
        Request request = new Request(socket.getInputStream());
        Response response = new Response(socket.getOutputStream());

        String content = handle(request, response);

        response.flush(content);
    }

    private String handle(Request request, Response response) {
        Pair<String, String> key = Pair.of(request.method(), request.path());
        Handler handler = handlers.get(key);

        if (handler == null) {
            return "";
        }

        View view = handler.handle(request, response);
        return view.render();
    }
}
