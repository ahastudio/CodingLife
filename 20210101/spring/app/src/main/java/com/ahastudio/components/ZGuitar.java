package com.ahastudio.components;

import org.springframework.stereotype.Component;

@Component("gopher")
public class ZGuitar {
    public ZGuitar() {
        System.out.println("\n*** Create Guitar\n");
    }

    public String sound() {
        return "I'm Z-Guitar!";
    }
}
