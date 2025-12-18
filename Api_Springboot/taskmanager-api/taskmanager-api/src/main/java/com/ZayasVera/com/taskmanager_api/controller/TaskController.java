package com.ZayasVera.com.taskmanager_api.controller;

import com.ZayasVera.com.taskmanager_api.model.Priority;
import com.ZayasVera.com.taskmanager_api.model.Task;
import com.ZayasVera.com.taskmanager_api.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // POST /api/tasks → Crear nueva tarea
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {
        Task savedTask = taskService.createTask(task);
        return new ResponseEntity<>(savedTask, HttpStatus.CREATED);
    }

    // GET /api/tasks → Obtener todas las tareas
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    // GET /api/tasks/{id} → Obtener tarea por ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    // PUT /api/tasks/{id} → Actualizar tarea completa
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @Valid @RequestBody Task task) {
        Task updatedTask = taskService.updateTask(id, task);
        return ResponseEntity.ok(updatedTask);
    }

    // PATCH /api/tasks/{id}/complete → Marcar como completada
    @PatchMapping("/{id}/complete")
    public ResponseEntity<Task> markAsCompleted(@PathVariable String id) {
        Task task = taskService.markAsCompleted(id);
        return ResponseEntity.ok(task);
    }

    // PATCH /api/tasks/{id}/incomplete → Marcar como no completada
    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<Task> markAsIncomplete(@PathVariable String id) {
        Task task = taskService.markAsIncomplete(id);
        return ResponseEntity.ok(task);
    }

    // DELETE /api/tasks/{id} → Eliminar tarea
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
    }

    // GET /api/tasks/completed → Tareas completadas
    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        List<Task> tasks = taskService.getCompletedTasks();
        return ResponseEntity.ok(tasks);
    }

    // GET /api/tasks/pending → Tareas pendientes
    @GetMapping("/pending")
    public ResponseEntity<List<Task>> getPendingTasks() {
        List<Task> tasks = taskService.getPendingTasks();
        return ResponseEntity.ok(tasks);
    }

    // GET /api/tasks/category/{category} → Buscar por categoría
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Task>> getTasksByCategory(@PathVariable String category) {
        List<Task> tasks = taskService.findByCategory(category);
        return ResponseEntity.ok(tasks);
    }

    // GET /api/tasks/tag/{tag} → Buscar por etiqueta
    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Task>> getTasksByTag(@PathVariable String tag) {
        List<Task> tasks = taskService.findByTag(tag);
        return ResponseEntity.ok(tasks);
    }

    // GET /api/tasks/priority/{priority} → Buscar por prioridad
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable Priority priority) {
        List<Task> tasks = taskService.findByPriority(priority);
        return ResponseEntity.ok(tasks);
    }

    // GET /api/tasks/search?title={title} → Buscar por título
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasksByTitle(@RequestParam String title) {
        List<Task> tasks = taskService.searchByTitle(title);
        return ResponseEntity.ok(tasks);
    }

    // (Opcional) Endpoint para tareas vencidas
    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdueTasks() {
        List<Task> tasks = taskService.getOverdueTasks(LocalDateTime.now());
        return ResponseEntity.ok(tasks);
    }
}
