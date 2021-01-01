package com.ahastudio.components;

import org.springframework.stereotype.Component;

@Component("oracle")
public class BookwarmOracle implements Oracle {
    private Encyclopedias encyclopedias;

    public void setEncyclopedias(Encyclopedias encyclopedias) {
        this.encyclopedias = encyclopedias;
    }

    @Override
    public String defineMeaningOfLife() {
        return "Encyclopedias are a waste of money - go see the world instead";
    }
}
