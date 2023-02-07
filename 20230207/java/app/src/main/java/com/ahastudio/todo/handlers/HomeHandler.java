package com.ahastudio.todo.handlers;

import com.ahastudio.todo.Service;
import com.ahastudio.todo.infrastructure.Request;
import com.ahastudio.todo.infrastructure.Response;
import com.ahastudio.todo.views.HomeView;
import com.ahastudio.todo.views.View;

public class HomeHandler implements Handler {
    private final Service service;

    public HomeHandler(Service service) {
        this.service = service;
    }

    @Override
    public View handle(Request request, Response response) {
        return new HomeView(service.tasks());
    }
}
