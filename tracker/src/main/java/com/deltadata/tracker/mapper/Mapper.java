package com.deltadata.tracker.mapper;

import com.deltadata.tracker.model.Task;
import com.deltadata.tracker.model.dto.TaskDto;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Mapper {
    public Task mapTaskDtoToTask(TaskDto taskDto) throws ParseException {
        Task task = new Task();
        if(null != taskDto.getId()){
            task.setId(taskDto.getId());
        }
        if(null != taskDto.getTitle()){
            task.setTitle(taskDto.getTitle());
        }
        if(null != taskDto.getDescription()){
            task.setDescription(taskDto.getDescription());
        }
        if(null != taskDto.getCompleted()){
            task.setCompleted(taskDto.getCompleted());
        }
        if(null != taskDto.getDueDate()){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = dateFormat.parse(taskDto.getDueDate());
            task.setDueDate(date);
        }
        return task;
    }
}
