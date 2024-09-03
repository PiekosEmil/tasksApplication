package com.emilpiekos.tasksapplication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TasksController {

    private final TaskRepository taskRepository;

    public TasksController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String completed) {
        List<Task> tasksList;

        if (completed == null) {
            tasksList = taskRepository.findAllOrderByExpireDate();
        } else if (completed.equals("true")) {
            tasksList = taskRepository.findAllByCompletedIsTrueOrderByExpireDate();
        } else {
            tasksList = taskRepository.findAllByCompletedIsFalseOrderByExpireDate();
        }

        model.addAttribute("categories", Category.values());
        model.addAttribute("tasks", tasksList);
        model.addAttribute("newTask", new Task());
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute("newTask") Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        Optional<Task> task = taskRepository.findById(id);
        if (task.isPresent()) {
            model.addAttribute("taskToEdit", task.get());
            model.addAttribute("categories", Category.values());
            return "edit";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/edit/task")
    public String editTask(@ModelAttribute("task") Task task) {
        taskRepository.deleteById(task.getId());
        taskRepository.save(task);
        return "redirect:/";
    }
}
