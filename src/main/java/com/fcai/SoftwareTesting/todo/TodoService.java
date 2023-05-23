package com.fcai.SoftwareTesting.todo;

import java.util.List;

public interface TodoService {

    public void setTodos(List<Todo> todos); // we made this to test null todos


    Todo create(TodoCreateRequest todo);

    Todo read(String id);

    Todo update(String id, boolean completed);

    void delete(String id);

    List<Todo> list();

    List<Todo> listCompleted();

}
