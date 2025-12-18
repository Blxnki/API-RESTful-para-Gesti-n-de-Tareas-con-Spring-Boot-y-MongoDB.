package com.ZayasVera.com.taskmanager_api.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String id) {
        super("No se encontró la tarea con ID: " + id);
    }
}
