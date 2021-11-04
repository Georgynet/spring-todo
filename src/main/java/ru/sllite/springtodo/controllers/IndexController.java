package ru.sllite.springtodo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.sllite.springtodo.dao.ToDoItemDao;

@Controller
public class IndexController {

    private final ToDoItemDao toDoItemDao;

    public IndexController(ToDoItemDao toDoItemDao) {
        this.toDoItemDao = toDoItemDao;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("toDoItems", toDoItemDao.getAll());
        return "index/index";
    }
}
