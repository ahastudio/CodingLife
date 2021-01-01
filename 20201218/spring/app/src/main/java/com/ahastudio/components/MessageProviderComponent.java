package com.ahastudio.components;

import com.ahastudio.Container;

public class MessageProviderComponent
        implements MessageProvider, ManagedComponent {
    @Override
    public String getMessage() {
        return "Hello, world!";
    }

    @Override
    public void performLookup(Container container) {
        // do nothing...
    }

    @Override
    public String toString() {
        return "안녕? 난 MessageProviderComponent라고 해~";
    }
}
