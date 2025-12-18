package com.ZayasVera.com.taskmanager_api.model;

//Con estas importaciones validamos los campos para comprobar que nos esten vacios o poner el tamaños máximo.
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//Simplificamos el código repetitivos como los getters, setters, constructores, etc...
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Con esto mapeamos esta clase a un documento en la base de datos.
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//En esta anotación indicamos que los objetos de esta clase se guardaran en la colección task de MongoDB.
@Document(collection = "tasks")
@Data //Generamos automaticamente los getters, setters, toString, equals y hashcode.
@NoArgsConstructor//Creamos un constructor sin argumentos, esto es necesario para freameworks como Spring.
@AllArgsConstructor//Creamos un constructor con todos los campos.
public class Task {
    @Id//Con esta etiqueta especifacamos que el identificador unico generado por MongoDB.
    private String id;

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 100, message = "El título no puede superar los 100 caracteres")
    private String title;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String description;

    private Boolean completed = false;

    @NotNull(message = "La prioridad es obligatoria")
    private Priority priority;
    private List<String> tags = new ArrayList<String>();
    private String category;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
    private LocalDateTime dueDate;
}
