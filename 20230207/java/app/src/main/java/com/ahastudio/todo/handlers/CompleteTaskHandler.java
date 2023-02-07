package com.ahastudio.todo.handlers;

import java.util.Map;

import com.ahastudio.todo.Service;
import com.ahastudio.todo.infrastructure.Request;
import com.ahastudio.todo.infrastructure.Response;
import com.ahastudio.todo.views.HomeView;
import com.ahastudio.todo.views.View;

public class CompleteTaskHandler implements Handler {
    private final Service service;

    public CompleteTaskHandler(Service service) {
        this.service = service;
    }

    @Override
    public View handle(Request request, Response response) {
        Map<String, String> formData = request.formData();
        int id = Integer.parseInt(formData.get("id"));

        service.completeTask(id);

        return new HomeView(service.tasks());
    }
}
