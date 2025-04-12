package com.jamlech.taskapp.domain.service.impl;

import com.jamlech.taskapp.domain.entities.Task;
import com.jamlech.taskapp.domain.entities.TaskList;
import com.jamlech.taskapp.domain.entities.TaskPriority;
import com.jamlech.taskapp.domain.entities.TaskStatus;
import com.jamlech.taskapp.domain.repositories.TaskListRepository;
import com.jamlech.taskapp.domain.repositories.TaskRepository;
import com.jamlech.taskapp.domain.service.TaskService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
@Service
public class TaskServicelmpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServicelmpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;
        this.taskListRepository = taskListRepository;
    }


    @Override
    public List<Task> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task createTask(UUID taskListId, Task task) {
        if(null !=task.getId()){
            throw new IllegalArgumentException("Task id is already set");
        }
        if (null==task.getTitle() || task.getTitle().isBlank()){
            throw new IllegalArgumentException("Task title is Blank.");
        }
        TaskPriority taskPriority=Optional.ofNullable(task.getPriority())
                .orElse(TaskPriority.MEDIUM);
        TaskStatus taskStatus =TaskStatus.TODO;
        TaskList taskList=taskListRepository.findById(taskListId).orElseThrow(
                () -> new IllegalArgumentException("Task list does not exist")
        );
        LocalDateTime now = LocalDateTime.now();
        Task taskToSave = new Task(
                null,
                task.getTitle(),
                task.getDescription(),
                task.getDueDate(),
                taskStatus,
                taskPriority,
                taskList,
                now,
                now
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<Task> getTask(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId,taskId);
    }

    @Override
    public Task updateTask(UUID taskListId, UUID taskId, Task task) {
       if (null==task.getId()){
           throw new IllegalArgumentException("Task id is already set.");
       }
       if (!Objects.equals(taskId, task.getId())){
           throw new IllegalArgumentException("Task id doesn't match.");
       }
       if (null==task.getPriority()){
           throw new IllegalArgumentException("Task priority is Blank.");
       }
       if (null==task.getStatus()){
           throw new IllegalArgumentException("Task status is Blank.");
       }
      Task existingTask= taskRepository.findByTaskListIdAndId(taskListId,taskId)
               .orElseThrow(()->new IllegalArgumentException("Task does not exist"));
       existingTask.setTitle(task.getTitle());
       existingTask.setDescription(task.getDescription());
       existingTask.setDueDate(task.getDueDate());
       existingTask.setStatus(task.getStatus());
       existingTask.setPriority(task.getPriority());
       existingTask.setUpdated(LocalDateTime.now());
       return taskRepository.save(existingTask);
    }

    @Transactional
    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {
        taskRepository.deleteByTaskListIdAndId(taskListId, taskId);
    }


}
