package ru.sllite.springtodo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sllite.springtodo.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findUserByUsername(String username);
}
