package ru.sllite.springtodo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sllite.springtodo.model.ToDoItem;

import java.util.List;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Integer> {
    List<ToDoItem> findAllByResolve(boolean resolve);

    List<ToDoItem> findAllByResolveAndToDoListId(boolean resolve, int id);
}
