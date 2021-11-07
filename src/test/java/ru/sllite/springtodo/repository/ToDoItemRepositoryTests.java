package ru.sllite.springtodo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.sllite.springtodo.model.ToDoItem;
import ru.sllite.springtodo.model.ToDoList;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class ToDoItemRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @Test
    public void testFindAllByResolve() {
        ToDoItem toDoItem = new ToDoItem("test 1", false);
        entityManager.persist(toDoItem);

        ToDoItem toDoItem2 = new ToDoItem("test 1", true);
        entityManager.persist(toDoItem2);

        List<ToDoItem> findAllByResolve = toDoItemRepository.findAllByResolve(toDoItem.isResolve());

        assertThat(findAllByResolve).extracting(ToDoItem::isResolve).containsOnly(toDoItem.isResolve());
    }

    @Test
    public void testFindAllByResolveAndToDoListId() {
        ToDoList toDoList = new ToDoList("test title");
        entityManager.persist(toDoList);

        ToDoItem toDoItem = new ToDoItem("test 1", false);
        toDoItem.setToDoList(toDoList);
        entityManager.persist(toDoItem);

        ToDoItem toDoItem2 = new ToDoItem("test 2", true);
        toDoItem2.setToDoList(toDoList);
        entityManager.persist(toDoItem2);

        List<ToDoItem> findAllByResolveAndToDoListId = toDoItemRepository
                .findAllByResolveAndToDoListId(toDoItem.isResolve(), toDoList.getId());

        assertThat(findAllByResolveAndToDoListId).extracting(ToDoItem::getText).containsOnly(toDoItem.getText());
    }
}
