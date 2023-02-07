package com.ahastudio.todo.views;

public abstract class View {
    private static final String LAYOUT = """
            <!DOCTYPE html>
            <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <title>ToDo</title>
                </head>
                <body>
                    <h1>
                        <a href="/">Home</a>
                    </h1>
                    %s
                </body>
            </html>
            """;

    public String render() {
        return String.format(LAYOUT, content());
    }

    protected abstract String content();
}
