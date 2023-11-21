package com.deltadata.tracker;

import com.deltadata.tracker.model.Task;
import com.deltadata.tracker.model.dto.TaskDto;
import com.deltadata.tracker.service.TaskService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class CRUDTest{

    private TaskDto testTask;

    @Autowired
    private TaskService taskService;

    @BeforeEach
    void setUp() throws ParseException {
        testTask = new TaskDto();
        testTask.setTitle("Task-1234 - This is Title of Task");
        testTask.setDescription("This is description of new task with code Task-1234");
        testTask.setCompleted(false);
        testTask.setDueDate("22/11/2023");
    }

    @Test
    void testCreateTask() throws ParseException {
        Task savedTask = taskService.createTask(testTask);
        assertNotNull(savedTask.getId());
        assertEquals(testTask.getTitle(), savedTask.getTitle());
        assertEquals(testTask.getDescription(), savedTask.getDescription());
        assertEquals(testTask.getCompleted(), savedTask.getCompleted());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(testTask.getDueDate());
        assertEquals(date, savedTask.getDueDate());
    }

    @Test
    void testGetTaskById() throws ParseException {
        Task savedTask = taskService.createTask(testTask);
        Long taskId = savedTask.getId();
        Task retrievedTask = taskService.getTaskById(taskId);
        assertNotNull(retrievedTask);
        assertEquals(savedTask, retrievedTask);
    }

    @Test
    void testGetAllTasks() throws ParseException {
        taskService.createTask(testTask);
        List<Task> taskList = taskService.getAllTask();
        assertFalse(taskList.isEmpty());
    }

    @Test
    void testUpdateTask() throws ParseException {
        // Create the task
        Task savedTask = taskService.createTask(testTask);
        Long taskId = savedTask.getId();

        // Update the task
        TaskDto updatedTask = new TaskDto();
        updatedTask.setId(taskId);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("This task has been updated");
        updatedTask.setCompleted(true);
        updatedTask.setDueDate("31/12/2023");

        Task result = taskService.updateTask(updatedTask, taskId);

        // Verify the updated task
        assertNotNull(result);
        assertEquals(updatedTask.getId(), result.getId());
        assertEquals(updatedTask.getTitle(), result.getTitle());
        assertEquals(updatedTask.getDescription(), result.getDescription());
        assertEquals(updatedTask.getCompleted(), result.getCompleted());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(updatedTask.getDueDate());
        assertEquals(date, result.getDueDate());
    }

    @Test
    void testUpdateTaskNotFound() {
        assertThrows(RuntimeException.class, () -> {
            taskService.updateTask(new TaskDto(),999L);
        });
    }

    @Test
    void testDeleteTask() throws ParseException {
        Task savedTask = taskService.createTask(testTask);
        Long taskId = savedTask.getId();
        taskService.deleteTask(taskId);
        assertThrows(NotFoundException.class, () -> {
            taskService.getTaskById(taskId);
        });
    }

}
