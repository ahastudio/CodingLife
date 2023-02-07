package com.ahastudio.todo.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private static final String CONTENT_SEPARATOR = "\n\n";

    private String method;
    private String path;
    private String content;
    private Map<String, String> formData = new HashMap<>();

    public Request(InputStream inputStream) throws IOException {
        parseMessage(readMessage(inputStream));
        parseFormData();
    }

    private String readMessage(InputStream inputStream) throws IOException {
        InputStreamReader reader = new InputStreamReader(inputStream);

        CharBuffer charBuffer = CharBuffer.allocate(1_000_000);
        reader.read(charBuffer);
        charBuffer.flip();

        return charBuffer.toString().replace("\r\n", "\n");
    }

    private void parseMessage(String message) {
        parseStartLine(message);
        parseContent(message);
    }

    private void parseStartLine(String message) {
        int index = message.indexOf("\n");
        String startLine = message.substring(0, index);

        String[] parts = startLine.split(" ");
        this.method = parts[0];
        this.path = parts[1];
    }

    private void parseContent(String message) {
        int index = message.indexOf(CONTENT_SEPARATOR);
        if (index < 0) {
            return;
        }
        this.content = message.substring(index).trim();
    }

    private void parseFormData() {
        if (content.isBlank()) {
            return;
        }

        String[] parts = content.split("&");
        Arrays.stream(parts).forEach(part -> {
            String[] pair = part.split("=");
            String key = pair[0];
            String value = URLDecoder.decode(pair[1], StandardCharsets.UTF_8);
            formData.put(key, value);
        });
    }

    public String method() {
        return method;
    }

    public String path() {
        return path;
    }

    public String content() {
        return content;
    }

    public Map<String, String> formData() {
        return new HashMap<>(formData);
    }
}
