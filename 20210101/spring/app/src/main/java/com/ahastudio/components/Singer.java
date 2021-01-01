package com.ahastudio.components;

import com.ahastudio.annotations.Trophy;
import org.springframework.stereotype.Component;

@Component("john-mayer")
@Trophy(name = {"grammay", "platinum diskk"})
//@Award({"grammay", "platinum diskk"})
//@Award(prize = {"grammay", "platinum diskk"})
//@Component("singer")
//@Scope("prototype")
public class Singer {
    private String lyrics = "Hello, how are you?";

    public String sing(String symbol) {
        return lyrics + symbol;
    }
}
