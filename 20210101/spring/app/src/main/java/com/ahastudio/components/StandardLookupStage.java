package com.ahastudio.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("standard-lookup-stage")
public class StandardLookupStage implements Stage {
    @Autowired
    private Singer singer;

    public StandardLookupStage() {
        //
    }

    // @Autowired
    public StandardLookupStage(Singer singer) {
        this.singer = singer;
    }

    // @Autowired
    public void setSingerObject(Singer singer) {
        this.singer = singer;
    }

    @Override
    public Singer getSinger() {
        return singer;
    }

    @Override
    public String show(String symbol) {
        return singer.sing(symbol);
    }
}
