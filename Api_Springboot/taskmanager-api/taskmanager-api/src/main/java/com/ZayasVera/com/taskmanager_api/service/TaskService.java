package com.ZayasVera.com.taskmanager_api.service;

import com.ZayasVera.com.taskmanager_api.model.Priority;
import com.ZayasVera.com.taskmanager_api.model.Task;
import java.util.List;
import java.time.LocalDateTime;

public interface TaskService {
    Task createTask(Task task);
    List<Task> getAllTasks();
    Task getTaskById(String id);
    Task updateTask(String id, Task task);
    void deleteTask(String id);
    Task markAsCompleted(String id);
    Task markAsIncomplete(String id);
    List<Task> getCompletedTasks();
    List<Task> getPendingTasks();
    List<Task> findByCategory(String category);
    List<Task> findByTag(String tag);
    List<Task> findByPriority(Priority priority);
    List<Task> searchByTitle(String title);
    List<Task> getOverdueTasks(LocalDateTime currentDate);
}
