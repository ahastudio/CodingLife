package utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sun.net.httpserver.HttpExchange;

public class FormParser {
    public Map<String, String> parse(String text) {
        return Arrays.stream(text.split("&"))
                .map(pair -> pair.split("="))
                .filter(pair -> pair.length == 2)
                .collect(Collectors.toMap(i -> i[0], i -> i[1]));
    }
}
