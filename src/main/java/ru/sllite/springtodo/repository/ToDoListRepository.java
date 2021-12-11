package ru.sllite.springtodo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sllite.springtodo.model.ToDoList;
import ru.sllite.springtodo.model.User;

import java.util.List;

public interface ToDoListRepository extends CrudRepository<ToDoList, Integer> {
    List<ToDoList> findAllByUser(User user);
}
