package com.ahastudio.api.rest.demo.models;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MultilineText {
    private final List<String> lines;

    public MultilineText(String text) {
        this.lines = Arrays.asList(text.split("\n"));
    }

    public static MultilineText of(String text) {
        return new MultilineText(text);
    }

    @Override
    public String toString() {
        return lines.stream().collect(Collectors.joining("\n"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultilineText that = (MultilineText) o;
        return Objects.equals(lines, that.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lines);
    }
}
