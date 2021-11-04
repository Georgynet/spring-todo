package ru.sllite.springtodo.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ToDoItem {
    private int id;

    @NotEmpty(message = "Text should not be empty")
    private String text;

    @NotNull(message = "Resolve should not be null")
    private boolean resolve = false;

    public ToDoItem() {
    }

    public ToDoItem(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isResolve() {
        return resolve;
    }

    public void setResolve(boolean resolve) {
        this.resolve = resolve;
    }
}
