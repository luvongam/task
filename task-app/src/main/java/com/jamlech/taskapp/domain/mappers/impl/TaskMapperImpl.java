package com.jamlech.taskapp.domain.mappers.impl;

import com.jamlech.taskapp.domain.dto.TaskDto;
import com.jamlech.taskapp.domain.entities.Task;
import com.jamlech.taskapp.domain.mappers.TaskMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task fromDto(TaskDto taskDto) {
        return new Task(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null

        );
    }

    @Override
    public TaskDto toDto(Task task) {
        return new TaskDto(
                        task.getId(),
                        task.getTitle(),
                        task.getDescription(),
                task.getDueDate(),
                task.getPriority(),
                task.getStatus()
                );
    }
}
