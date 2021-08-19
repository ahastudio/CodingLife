package com.ahastudio.components;

public class StandardOutMessageRenderer implements MessageRenderer {
    private final MessageProvider messageProvider;

    public StandardOutMessageRenderer(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public void render() {
        System.out.println(messageProvider.getMessage());
    }
}
