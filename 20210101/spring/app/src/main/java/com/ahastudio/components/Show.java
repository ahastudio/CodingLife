package com.ahastudio.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Show {
    @Autowired
//    @Qualifier("stage1")
    public Stage stage;

    @Autowired
    @Qualifier("stage2")
    public Stage stageTwo;

    public String show() {
        return stage.show("") + " & " + stageTwo.show("");
    }
}
