package com.jamlech.taskapp.domain.mappers;

import com.jamlech.taskapp.domain.dto.TaskDto;
import com.jamlech.taskapp.domain.entities.Task;

public interface TaskMapper  {
    Task fromDto(TaskDto taskDto);


    TaskDto toDto(Task task);
}
