package ru.sllite.springtodo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.sllite.springtodo.enums.AuthorityType;
import ru.sllite.springtodo.model.Authority;

import java.util.Optional;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Optional<Authority> findAuthorityByName(AuthorityType name);
}
