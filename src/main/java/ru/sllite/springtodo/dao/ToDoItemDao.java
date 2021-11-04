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
        return jdbcTemplate.query("SELECT * FROM todo_item ORDER BY id", new BeanPropertyRowMapper<>(ToDoItem.class));
    }

    public void resolve(int id) {
        jdbcTemplate.update("UPDATE todo_item SET resolve = true WHERE id = ?", id);
    }

    public void open(int id) {
        jdbcTemplate.update("UPDATE todo_item SET resolve = false WHERE id = ?", id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM todo_item WHERE id = ?", id);
    }

    public void save(ToDoItem toDoItem) {
        jdbcTemplate.update("INSERT INTO todo_item (text) VALUES (?)", toDoItem.getText());
    }
}
