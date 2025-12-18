package com.ZayasVera.com.taskmanager_api.service;

import com.ZayasVera.com.taskmanager_api.exception.TaskNotFoundException;
import com.ZayasVera.com.taskmanager_api.model.Priority;
import com.ZayasVera.com.taskmanager_api.model.Task;
import com.ZayasVera.com.taskmanager_api.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor // Inyección automática del repositorio gracias a Lombok
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public Task createTask(Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        // Asegúrate de que completed sea false por defecto si no se indica
        if (task.getCompleted() == null) {
            task.setCompleted(false);
        }
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task updateTask(String id, Task updatedTask) {
        Task existingTask = getTaskById(id); // Lanza excepción si no existe

        // Actualiza solo los campos que vienen en la petición
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setCategory(updatedTask.getCategory());
        existingTask.setTags(updatedTask.getTags());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setCompleted(updatedTask.getCompleted());
        existingTask.setUpdatedAt(LocalDateTime.now());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(String id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task markAsCompleted(String id) {
        Task task = getTaskById(id);
        task.setCompleted(true);
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task markAsIncomplete(String id) {
        Task task = getTaskById(id);
        task.setCompleted(false);
        task.setUpdatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getCompletedTasks() {

        return taskRepository.findByCompleted(true);
    }

    @Override
    public List<Task> getPendingTasks() {

        return taskRepository.findByCompleted(false);
    }

    @Override
    public List<Task> findByCategory(String category) {

        return taskRepository.findByCategory(category);
    }

    @Override
    public List<Task> findByTag(String tag) {

        return taskRepository.findByTagsContaining(tag);
    }

    @Override
    public List<Task> findByPriority(Priority priority) {

        return taskRepository.findByPriority(priority);
    }

    @Override
    public List<Task> searchByTitle(String title) {

        return taskRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Task> getOverdueTasks(LocalDateTime currentDate) {
        return taskRepository.findByDueDateBefore(currentDate);
    }
}