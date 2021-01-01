package com.ahastudio.components;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Stage1 implements Stage {
    private final Singer singer;

    public Stage1(Singer singer) {
        this.singer = singer;
    }

    @Override
    public Singer getSinger() {
        return singer;
    }

    @Override
    public String show(String symbol) {
        return "Stage #1";
    }
}
