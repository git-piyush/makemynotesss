package com.piyush.InventoryManagementSystem.service;

import com.piyush.InventoryManagementSystem.dto.Response;
import com.piyush.InventoryManagementSystem.entity.ToDo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TodoService {

    Response createTodo(ToDo todo);

    Response updateTodo(ToDo todo);

    Response deleteTodo(Long id);

    List<ToDo> getAllTodoByUser();


    void deleteTodoByUserid();

    void deleteById(Long id);
}
