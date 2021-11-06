package ru.sllite.springtodo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sllite.springtodo.model.ToDoList;

public interface ToDoListRepository extends CrudRepository<ToDoList, Integer> {
}
