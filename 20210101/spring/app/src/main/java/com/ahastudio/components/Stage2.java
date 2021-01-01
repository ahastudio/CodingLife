package com.ahastudio.components;

import org.springframework.stereotype.Component;

@Component
public class Stage2 implements Stage {
    private final Singer singer;

    public Stage2(Singer singer) {
        this.singer = singer;
    }

    @Override
    public Singer getSinger() {
        return singer;
    }

    @Override
    public String show(String symbol) {
        return "Stage #2";
    }
}
