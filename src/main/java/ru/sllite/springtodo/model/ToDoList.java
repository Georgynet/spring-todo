package ru.sllite.springtodo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class ToDoList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Title should not be empty")
    private String title;

    @OneToMany(mappedBy = "toDoList")
    private List<ToDoItem> toDoItems;

    public ToDoList() {
    }

    public ToDoList(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ToDoItem> getToDoItems() {
        return toDoItems;
    }
}
