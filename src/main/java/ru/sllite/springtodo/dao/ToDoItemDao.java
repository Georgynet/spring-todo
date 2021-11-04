package ru.sllite.springtodo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.sllite.springtodo.models.ToDoItem;

import java.util.List;

@Component
public class ToDoItemDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ToDoItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ToDoItem> getAll() {
        return jdbcTemplate.query("SELECT * FROM todo_item", new BeanPropertyRowMapper<>(ToDoItem.class));
    }
}
