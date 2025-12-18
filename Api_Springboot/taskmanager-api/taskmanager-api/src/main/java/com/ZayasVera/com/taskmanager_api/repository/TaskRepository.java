package com.ZayasVera.com.taskmanager_api.repository;

import com.ZayasVera.com.taskmanager_api.model.Priority;
import com.ZayasVera.com.taskmanager_api.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

//Al extender a MongoRepository, especificamos la clase del modelo en este caso seria task y el String viene del tipo de campo de la id.
//Esto nos da una serie de metodos muy buenos como findAll(), findById(String id), save(), deleteById,etc.
public interface TaskRepository extends MongoRepository<Task, String> {
    // Métodos de consulta personalizados
    List<Task> findByCompleted(boolean completed);//Busca tareas por su estado de finalización.
    List<Task> findByCategory(String category);//Filtra por categoría exacta
    List<Task> findByTagsContaining(String tag);//Busca por etiqueta
    List<Task> findByPriority(Priority priority);//Busca por prioridad
    List<Task> findByTitleContainingIgnoreCase(String title);//Busca por título
    List<Task> findByDueDateBefore(LocalDateTime date);//Busca por fecha.
}


