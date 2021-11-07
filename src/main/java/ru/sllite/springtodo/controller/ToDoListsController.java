package ru.sllite.springtodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sllite.springtodo.model.ToDoList;
import ru.sllite.springtodo.repository.ToDoListRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo-lists")
public class ToDoListsController {

    private final ToDoListRepository toDoListRepository;

    public ToDoListsController(ToDoListRepository toDoListRepository) {
        this.toDoListRepository = toDoListRepository;
    }

    @PostMapping()
    public String create(@ModelAttribute("newToDoList") @Valid ToDoList newToDoList, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newToDoList", bindingResult);
            redirectAttributes.addFlashAttribute("newToDoList", newToDoList);
            return "redirect:/";
        }

        toDoListRepository.save(newToDoList);
        return String.format("redirect:/list/%s", newToDoList.getId());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ToDoList toDoList = toDoListRepository.findById(id).stream().findAny().orElseThrow();
        toDoListRepository.delete(toDoList);
        return "redirect:/";
    }
}
