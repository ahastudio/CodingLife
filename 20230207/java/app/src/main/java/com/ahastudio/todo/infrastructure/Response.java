package com.ahastudio.todo.infrastructure;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Response {
    private static final String MESSAGE = """
            HTTP/1.1 200 OK
            Content-Type: text/html; charset=UTF-8
            "Content-Length: %d
                            
            %s
            """;

    private final Writer writer;

    public Response(OutputStream outputStream) {
        writer = new OutputStreamWriter(outputStream);
    }

    public void flush(String content) throws IOException {
        byte[] bytes = content.getBytes();
        String message = String.format(MESSAGE, bytes.length, content);
        writer.write(message);
        writer.flush();
    }
}
