package com.ahastudio.components;

import com.ahastudio.Container;

public class MessageRendererComponent
        implements MessageRenderer, ManagedComponent {
    private MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            System.out.println("👿 그렇게 살지 마세요.");
            return;
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }

    @Override
    public void performLookup(Container container) {
        MessageProvider provider =
                (MessageProvider) container.getDependency("message-provider");

        setMessageProvider(provider);
    }

    @Override
    public String toString() {
        return messageProvider.toString();
    }
}
