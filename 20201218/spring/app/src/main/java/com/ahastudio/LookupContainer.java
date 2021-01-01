package com.ahastudio;

import com.ahastudio.components.ManagedComponent;
import com.ahastudio.components.MessageProviderComponent;
import com.ahastudio.components.MessageRendererComponent;

import java.util.HashMap;
import java.util.Map;

public class LookupContainer implements Container {
    private Map<String, ManagedComponent> components = new HashMap<>();

    public LookupContainer() {
        // 컴포넌트 등록
        components.put("message-renderer", new MessageRendererComponent());
        components.put("message-provider", new MessageProviderComponent());

        // Contextualized Dependency Lookup!
        components.values().forEach(component -> {
            component.performLookup(this);
        });
    }

    @Override
    public Object getDependency(String key) {
        return components.get(key);
    }
}
