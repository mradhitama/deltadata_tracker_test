package com.deltadata.tracker.model.dto;

import lombok.Data;

@Data
public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Boolean completed;
    private String dueDate;
}
