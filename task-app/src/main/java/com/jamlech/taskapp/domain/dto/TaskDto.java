package com.jamlech.taskapp.domain.dto;

import com.jamlech.taskapp.domain.entities.TaskPriority;
import com.jamlech.taskapp.domain.entities.TaskStatus;

import java.time.LocalDate;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDate dueDate,
        TaskPriority priority,
        TaskStatus status
) {

}
