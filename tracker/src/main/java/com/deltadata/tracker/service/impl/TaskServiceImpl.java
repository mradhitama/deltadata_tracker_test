package com.deltadata.tracker.service.impl;

import com.deltadata.tracker.mapper.Mapper;
import com.deltadata.tracker.model.Task;
import com.deltadata.tracker.model.dto.TaskDto;
import com.deltadata.tracker.repository.TaskRepository;
import com.deltadata.tracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Mapper mapper;


    private final String DATE_FORMAT = "dd/MM/yyyy";
    @Override
    public List<Task> getAllTask() {
        List<Task> taskList = taskRepository.findAll();
        if(taskList.isEmpty()){
            return new ArrayList<Task>();
        }
        return taskList;
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if(optionalTask.isEmpty()){
            throw new NotFoundException("Task with id "+id+" is not found");
        }
        return optionalTask.get();
    }

    @Override
    public Task createTask(TaskDto taskDto) throws ParseException {
        if(null == taskDto.getTitle()){
            throw new NullPointerException("Title cannot be null");
        }
        if(null != taskDto.getDueDate() && !isValidDateFormat(taskDto.getDueDate())){
            throw new IllegalArgumentException("Date format is not valid");
        }
        taskDto.setCompleted(false);
        Task task = mapper.mapTaskDtoToTask(taskDto);
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public Task updateTask(TaskDto taskDto, Long id) {
        try {
            if(null == id){
                throw new IllegalArgumentException("Parameter id cannot be null");
            }
            Task existingTask = this.getTaskById(id);
            if(null == existingTask){
                throw new IllegalArgumentException("Task with ID "+id+" is not exists");
            }
            if(!id.equals(taskDto.getId())){
                throw new IllegalArgumentException("Id in request body does not match Task's Id");
            }
            if(null == taskDto.getTitle()){
                throw new NullPointerException("Title cannot be null");
            }
            if(null != taskDto.getDueDate() && !isValidDateFormat(taskDto.getDueDate())){
                throw new IllegalArgumentException("Date format is not valid");
            }
            Task task = mapper.mapTaskDtoToTask(taskDto);
            return taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteTask(Long id) {
        try {
            Task existingTask = this.getTaskById(id);
            if(null == existingTask){
                throw new IllegalArgumentException("Task with ID "+id+" is not exists");
            }
            taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Task updateDueDate(TaskDto taskDto, Long id) {
        try {
            if(null == id){
                throw new IllegalArgumentException("Parameter id cannot be null");
            }
            Task existingTask = this.getTaskById(id);
            if(null == existingTask){
                throw new IllegalArgumentException("Task with ID "+id+" is not exists");
            }
            if(null != taskDto.getDueDate() && !isValidDateFormat(taskDto.getDueDate())){
                throw new IllegalArgumentException("Date format is not valid");
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date date = dateFormat.parse(taskDto.getDueDate());
            existingTask.setDueDate(date);
            return taskRepository.save(existingTask);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private boolean isValidDateFormat(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            sdf.setLenient(false);
            Date date = sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            throw new IllegalArgumentException("Date format is not valid");
        }
    }
}
