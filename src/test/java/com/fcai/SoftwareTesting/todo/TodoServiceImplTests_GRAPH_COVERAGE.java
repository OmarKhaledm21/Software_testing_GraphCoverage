package com.fcai.SoftwareTesting.todo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TodoServiceImplTests_GRAPH_COVERAGE {
    TodoService todoService = new TodoServiceImpl();

    /**
     * This is unit tests for Create function
     */
    @Test
    public void testCreateTodo() {
        TodoCreateRequest request = new TodoCreateRequest("Title1", "Description");
        Todo createdTodo = todoService.create(request);

        Assertions.assertNotNull(createdTodo);
        Assertions.assertEquals("Title1", createdTodo.getTitle());
        Assertions.assertEquals("Description", createdTodo.getDescription());
        Assertions.assertFalse(createdTodo.isCompleted());
    }

    @Test
    public void testCreateTodoWithNullRequest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.create(null);
        });
    }

    @Test
    public void testCreateTodoWithEmptyTitle() {
        TodoCreateRequest request = new TodoCreateRequest("", "Description");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.create(request);
        });
    }

    @Test
    public void testCreateTodoWithEmptyDescription() {
        TodoCreateRequest request = new TodoCreateRequest("Title", "");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.create(request);
        });
    }
    /**
     * END Create Unit tests.
     * */
    /**
     * Read method Unit tests
     */
    @Test
    public void testReadTodoWithNullId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.read(null);
        });
    }

    @Test
    public void testReadTodoWithEmptyId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.read("");
        });
    }

    @Test
    public void testReadTodoWithNullTodo() {
        /**
         * PRECONDITION: NO TODO ITEM WITH ID 831741*/
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.read("831741");
        });
    }

    @Test
    public void testReadTodo() {
        todoService = new TodoServiceImpl();
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        todoCreateRequest.setTitle("Title");
        todoCreateRequest.setDescription("Description");
        todoService.create(todoCreateRequest);

        Todo todo = todoService.read("1");
        Assertions.assertNotNull(todo);
        Assertions.assertEquals("Title", todo.getTitle());
        Assertions.assertEquals("Description", todo.getDescription());
        Assertions.assertFalse(todo.isCompleted());
    }

    @Test
    public void testReadTodo2() {
        todoService = new TodoServiceImpl();
        TodoCreateRequest todoCreateRequest = new TodoCreateRequest();
        TodoCreateRequest todoCreateRequest2 = new TodoCreateRequest();
        todoCreateRequest.setTitle("Title");
        todoCreateRequest.setDescription("Description");
        todoCreateRequest2.setTitle("Title2");
        todoCreateRequest2.setDescription("Description2");

        todoService.create(todoCreateRequest);
        todoService.create(todoCreateRequest2);

        Todo todo = todoService.read("2");
        Assertions.assertNotNull(todo);
        Assertions.assertEquals("Title2", todo.getTitle());
        Assertions.assertEquals("Description2", todo.getDescription());
        Assertions.assertFalse(todo.isCompleted());
    }
    /**
     * END Unit Tests Read Method
     * */
    /**
     * Unit tests for Delete, Update & List methods
     */
    @Test
    public void testDeleteTodo() {
        todoService = new TodoServiceImpl();
        todoService.create(new TodoCreateRequest("Title 1", "Description 1"));
        todoService.create(new TodoCreateRequest("Title 2", "Description 2"));
        todoService.create(new TodoCreateRequest("Title 3", "Description 3"));

        String id = "2";
        todoService.delete(id);
        Assertions.assertEquals(2, todoService.list().size());
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.read(id);
        });
    }

    @Test
    public void testDeleteNonExistingTodo() {
        todoService = new TodoServiceImpl();
        todoService.create(new TodoCreateRequest("Title 1", "Description 1"));
        todoService.create(new TodoCreateRequest("Title 2", "Description 2"));
        todoService.create(new TodoCreateRequest("Title 3", "Description 3"));

        String id = "4";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.delete(id);
        });
    }

    @Test
    public void testUpdateTodo() {
        todoService = new TodoServiceImpl();
        todoService.create(new TodoCreateRequest("Title 1", "Description 1"));
        String id = "1";
        boolean completed = true;

        Todo updatedTodo = todoService.update(id, completed);

        Assertions.assertNotNull(updatedTodo);
        Assertions.assertEquals(id, updatedTodo.getId());
        Assertions.assertEquals(completed, updatedTodo.isCompleted());
    }

    @Test
    public void testUpdateNonExistingTodo() {
        todoService = new TodoServiceImpl();
        String id = "23";
        boolean completed = true;

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            todoService.update(id, completed);
        });
    }

    @Test
    public void testList() {
        todoService = new TodoServiceImpl();
        Assertions.assertNotNull(todoService.list());
    }

    @Test
    public void testListWithData() {
        todoService = new TodoServiceImpl();
        todoService.create(new TodoCreateRequest("Title 1", "Description 1"));
        todoService.create(new TodoCreateRequest("Title 2", "Description 2"));
        todoService.create(new TodoCreateRequest("Title 3", "Description 3"));

        Assertions.assertEquals(3, todoService.list().size());
    }

    /**
     * END Unit Tests Update, Delete, List Methods
     * */
    /**
     * Unit tests for ListCompleted
     */
    @Test
    public void testListCompletedTodos() {
        todoService = new TodoServiceImpl();
        todoService.create(new TodoCreateRequest("Title 1", "Description 1"));
        todoService.create(new TodoCreateRequest("Title 2", "Description 2"));
        todoService.create(new TodoCreateRequest("Title 3", "Description 3"));
        todoService.update("2", true);
        List<Todo> result = todoService.listCompleted();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Title 2", result.get(0).getTitle());
    }

}