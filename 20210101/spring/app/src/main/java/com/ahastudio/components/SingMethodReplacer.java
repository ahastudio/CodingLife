package com.ahastudio.components;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class SingMethodReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args)
            throws Throwable {
        if (!method.getName().equals("sing")) {
            throw new IllegalArgumentException("Unable to reimplement");
        }

        return "Hacked..." + args[0];
    }
}
