package com.deltadata.tracker.service;

import com.deltadata.tracker.model.Task;
import com.deltadata.tracker.model.dto.TaskDto;

import java.text.ParseException;
import java.util.List;

public interface TaskService {
    public List<Task> getAllTask();
    public Task getTaskById(Long id);

    public Task createTask(TaskDto taskDto) throws ParseException;

    public Task updateTask(TaskDto taskDto, Long id);

    public void deleteTask(Long id);

    Task updateDueDate(TaskDto taskDto, Long id);
}
