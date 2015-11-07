package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.domain.Task;
import com.example.repository.TaskRepository;

@Controller
@RequestMapping("/")
public class TaskController {
    @Autowired
    private TaskRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap modelMap) {
	List<Task> tasks = repository.findAll();
	modelMap.put("tasks", tasks);
	return "task/index";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Task task) {
	repository.save(task);
	return "redirect:/";
    }
}
