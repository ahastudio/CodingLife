package com.ahastudio.todo.handlers;

import com.ahastudio.todo.infrastructure.Request;
import com.ahastudio.todo.infrastructure.Response;
import com.ahastudio.todo.views.View;

public interface Handler {
    View handle(Request request, Response response);
}
