package ru.sllite.springtodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sllite.springtodo.model.ToDoItem;
import ru.sllite.springtodo.repository.ToDoItemRepository;

@Controller
public class IndexController {

    public ToDoItemRepository toDoItemRepository;

    public IndexController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("notResolvedToDoItems", toDoItemRepository.findAllByResolve(false));
        model.addAttribute("resolvedToDoItems", toDoItemRepository.findAllByResolve(true));
        if (!model.containsAttribute("newToDoItem")) {
            model.addAttribute("newToDoItem", new ToDoItem());
        }
        return "index/index";
    }
}
