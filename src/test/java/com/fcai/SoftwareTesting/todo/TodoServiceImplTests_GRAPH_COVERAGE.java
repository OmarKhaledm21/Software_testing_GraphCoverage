package com.fcai.SoftwareTesting.todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class TodoServiceImplTests_GRAPH_COVERAGE {
    /**
     * This is unit tests for Create function
     * */
    @Test
    public void testCreateTodo() {
        TodoCreateRequest request = new TodoCreateRequest("Title", "Description");
        TodoService todoService = new TodoServiceImpl();
        Todo createdTodo = todoService.create(request);

        Assertions.assertNotNull(createdTodo);
        Assertions.assertEquals("Title", createdTodo.getTitle());
        Assertions.assertEquals("Description", createdTodo.getDescription());
        Assertions.assertFalse(createdTodo.isCompleted());
    }

    @Test
    public void testCreateTodoWithNullRequest() {
        TodoService todoService = new TodoServiceImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.create(null);
        });
    }

    @Test
    public void testCreateTodoWithEmptyTitle() {
        TodoCreateRequest request = new TodoCreateRequest("", "Description");
        TodoService todoService = new TodoServiceImpl();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.create(request);
        });
    }

    @Test
    public void testCreateTodoWithEmptyDescription() {
        TodoCreateRequest request = new TodoCreateRequest("Title", "");
        TodoService todoService = new TodoServiceImpl();

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.create(request);
        });
    }
    /**
     * END Create Unit tests.
     * */
    /**
     * Read method Unit tests
     * */








}
