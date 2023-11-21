package com.deltadata.tracker.controller;

import com.deltadata.tracker.model.Task;
import com.deltadata.tracker.model.dto.TaskDto;
import com.deltadata.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<Object> getAllTask (){
        List<Task> taskList = taskService.getAllTask();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Task loaded successfully");
        response.put("data", taskList);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Object> getTaskById (@PathVariable Long id){
        try {
            Task task = taskService.getTaskById(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Task loaded successfully");
            response.put("data", task);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/tasks")
    public ResponseEntity<Object> createTask (@RequestBody TaskDto taskDto){
        try {
            Task newTask = taskService.createTask(taskDto);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Task created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PutMapping("/tasks/{id}")
    @ResponseBody
    public ResponseEntity<?> updateTask (@RequestBody TaskDto taskDto, @PathVariable Long id) {
        try {
            Task updateTask = taskService.updateTask(taskDto, id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Task with Id " + updateTask.getId() + " updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @DeleteMapping("/tasks/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteTaskData(@PathVariable Long id) {
        try {
            taskService.deleteTask(id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Task with id " + id + " deleted successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

    }

    @PutMapping("/duedate/{id}")
    @ResponseBody
    public ResponseEntity<?> updateDueDate (@RequestBody TaskDto taskDto, @PathVariable Long id) {
        try {
            Task updateTask = taskService.updateDueDate(taskDto, id);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Task with Id " + updateTask.getId() + " updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
