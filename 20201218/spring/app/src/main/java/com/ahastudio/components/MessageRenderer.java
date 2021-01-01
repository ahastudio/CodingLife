package com.ahastudio.components;

public interface MessageRenderer {
    void render();

    void setMessageProvider(MessageProvider messageProvider);
    MessageProvider getMessageProvider();
}
