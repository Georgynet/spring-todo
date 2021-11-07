package ru.sllite.springtodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.sllite.springtodo.model.ToDoItem;
import ru.sllite.springtodo.model.ToDoList;
import ru.sllite.springtodo.repository.ToDoItemRepository;
import ru.sllite.springtodo.repository.ToDoListRepository;

@Controller
public class IndexController {

    public ToDoListRepository toDoListRepository;

    public ToDoItemRepository toDoItemRepository;

    public IndexController(ToDoListRepository toDoListRepository,ToDoItemRepository toDoItemRepository) {
        this.toDoListRepository = toDoListRepository;
        this.toDoItemRepository = toDoItemRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("allLists", toDoListRepository.findAll());

        if (!model.containsAttribute("newToDoItem")) {
            model.addAttribute("newToDoItem", new ToDoItem());
        }
        if (!model.containsAttribute("newToDoList")) {
            model.addAttribute("newToDoList", new ToDoList());
        }

        return "index/index";
    }

    @GetMapping("/list/{id}")
    public String list(@PathVariable("id") int id, Model model) {
        model.addAttribute("allLists", toDoListRepository.findAll());

        model.addAttribute("currentListId", id);
        model.addAttribute("notResolvedToDoItems", toDoItemRepository.findAllByResolveAndToDoListId(false, id));
        model.addAttribute("resolvedToDoItems", toDoItemRepository.findAllByResolveAndToDoListId(true, id));

        if (!model.containsAttribute("newToDoItem")) {
            model.addAttribute("newToDoItem", new ToDoItem());
        }
        if (!model.containsAttribute("newToDoList")) {
            model.addAttribute("newToDoList", new ToDoList());
        }

        return "index/list";
    }
}
