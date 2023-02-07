package com.ahastudio.todo.handlers;

import java.util.Map;

import com.ahastudio.todo.Service;
import com.ahastudio.todo.infrastructure.Request;
import com.ahastudio.todo.infrastructure.Response;
import com.ahastudio.todo.views.HomeView;
import com.ahastudio.todo.views.View;

public class CreateTaskHandler implements Handler {
    private final Service service;

    public CreateTaskHandler(Service service) {
        this.service = service;
    }

    @Override
    public View handle(Request request, Response response) {
        Map<String, String> formData = request.formData();

        service.addTask(formData.get("title"));

        return new HomeView(service.tasks());
    }
}
