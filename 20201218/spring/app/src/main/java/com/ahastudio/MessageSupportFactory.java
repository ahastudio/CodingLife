package com.ahastudio;

import com.ahastudio.components.MessageProvider;
import com.ahastudio.components.MessageRenderer;

import java.util.Properties;

public class MessageSupportFactory {
    private static final String PROPERTIES_NAME =
            "/message-support-factory.properties";

    private static MessageSupportFactory instance;

    private Properties properties;
    private MessageRenderer renderer;
    private MessageProvider provider;

    private MessageSupportFactory() throws Exception {
        properties = new Properties();
        properties.load(getClass().getResourceAsStream(PROPERTIES_NAME));

        renderer = (MessageRenderer) newInstance("renderer.class");
        provider = (MessageProvider) newInstance("provider.class");
    }

    private Object newInstance(String key) throws Exception {
        return Class.forName(properties.getProperty(key))
                .getDeclaredConstructor()
                .newInstance();
    }

    static {
        try {
            instance = new MessageSupportFactory();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static MessageSupportFactory getInsatance() {
        return instance;
    }

    public MessageRenderer getMessageRenderer() {
        return renderer;
    }

    public MessageProvider getMessageProvider() {
        return provider;
    }
}
