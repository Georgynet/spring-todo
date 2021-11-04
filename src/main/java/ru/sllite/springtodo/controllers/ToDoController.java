package ru.sllite.springtodo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sllite.springtodo.dao.ToDoItemDao;
import ru.sllite.springtodo.models.ToDoItem;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo-items")
public class ToDoController {

    private final ToDoItemDao toDoItemDao;

    public ToDoController(ToDoItemDao toDoItemDao) {
        this.toDoItemDao = toDoItemDao;
    }

    @PostMapping()
    public String create(@ModelAttribute("newToDoItem") @Valid ToDoItem newToDoItem, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            // keep form field values and errors after redirection
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newToDoItem", bindingResult);
            redirectAttributes.addFlashAttribute("newToDoItem", newToDoItem);
            return "redirect:/";
        }

        toDoItemDao.save(newToDoItem);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        toDoItemDao.delete(id);
        return "redirect:/";
    }

    @PatchMapping("/resolve/{id}")
    public String resolve(@PathVariable("id") int id) {
        toDoItemDao.resolve(id);
        return "redirect:/";
    }

    @PatchMapping("/open/{id}")
    public String open(@PathVariable("id") int id) {
        toDoItemDao.open(id);
        return "redirect:/";
    }
}
