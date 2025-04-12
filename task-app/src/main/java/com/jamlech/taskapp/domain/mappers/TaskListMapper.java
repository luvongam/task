package com.jamlech.taskapp.domain.mappers;

import com.jamlech.taskapp.domain.dto.TaskDto;
import com.jamlech.taskapp.domain.dto.TaskListDto;
import com.jamlech.taskapp.domain.entities.Task;
import com.jamlech.taskapp.domain.entities.TaskList;

public interface TaskListMapper {
    TaskList fromDto(TaskListDto taskListDto);


    TaskListDto toDto(TaskList taskList);
}