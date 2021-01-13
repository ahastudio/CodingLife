package com.ahastudio.money;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Double> rates = new HashMap<>();

    public double rate(String from, String to) {
        return rates.getOrDefault(from + "-" + to, 1.0);
    }

    public void setRate(String from, String to, double rate) {
        rates.put(from + "-" + to, rate);
    }
}
