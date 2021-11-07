package ru.sllite.springtodo.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ToDoList toDoList;

    @Column(nullable = false)
    @NotEmpty(message = "Text should not be empty")
    private String text;

    @NotNull(message = "Resolve should not be null")
    private boolean resolve = false;

    @Column(nullable = false)
    @UpdateTimestamp
    private Date updated;

    @Column(nullable = false)
    @CreationTimestamp
    private Date created;

    public ToDoItem() {
    }

    public ToDoItem(String text, boolean resolve) {
        this.text = text;
        this.resolve = resolve;
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

    public ToDoList getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
