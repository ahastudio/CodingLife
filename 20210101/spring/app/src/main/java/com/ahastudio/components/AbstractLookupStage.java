package com.ahastudio.components;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component("abstract-lookup-stage")
public class AbstractLookupStage implements Stage {
    @Lookup("singer")
    public Singer getSinger() {
        return null;
    }

    @Override
    public String show(String symbol) {
        return getSinger().sing(symbol);
    }
}
