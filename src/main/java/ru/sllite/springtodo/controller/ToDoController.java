package ru.sllite.springtodo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.sllite.springtodo.model.ToDoItem;
import ru.sllite.springtodo.model.ToDoList;
import ru.sllite.springtodo.repository.ToDoItemRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo-items")
public class ToDoController {

    public ToDoItemRepository toDoItemRepository;

    public ToDoController(ToDoItemRepository toDoItemRepository) {
        this.toDoItemRepository = toDoItemRepository;
    }

    @PostMapping()
    public String create(@ModelAttribute("newToDoItem") @Valid ToDoItem newToDoItem, BindingResult bindingResult,
                         @ModelAttribute(value = "currentListId") ToDoList toDoList,
                         RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            // keep form field values and errors after redirection
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newToDoItem", bindingResult);
            redirectAttributes.addFlashAttribute("newToDoItem", newToDoItem);
            return String.format("redirect:/list/%s", toDoList.getId());
        }

        newToDoItem.setToDoList(toDoList);
        toDoItemRepository.save(newToDoItem);
        return String.format("redirect:/list/%s", toDoList.getId());
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        ToDoItem toDoItem = toDoItemRepository.findById(id).stream().findAny().orElseThrow();
        ToDoList toDoList = toDoItem.getToDoList();
        toDoItemRepository.delete(toDoItem);
        return String.format("redirect:/list/%s", toDoList.getId());
    }

    @PatchMapping("/resolve/{id}")
    public String resolve(@PathVariable("id") int id) {
        ToDoItem toDoItem = toDoItemRepository.findById(id).stream().findAny().orElseThrow();
        toDoItem.setResolve(true);
        toDoItemRepository.save(toDoItem);
        return String.format("redirect:/list/%s", toDoItem.getToDoList().getId());
    }

    @PatchMapping("/open/{id}")
    public String open(@PathVariable("id") int id) {
        ToDoItem toDoItem = toDoItemRepository.findById(id).stream().findAny().orElseThrow();
        toDoItem.setResolve(false);
        toDoItemRepository.save(toDoItem);
        return String.format("redirect:/list/%s", toDoItem.getToDoList().getId());
    }
}
