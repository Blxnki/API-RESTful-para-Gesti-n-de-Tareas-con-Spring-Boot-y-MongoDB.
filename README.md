# TaskManager API

API RESTful para la gestión de tareas (tipo Google Keep), desarrollada con **Spring Boot** y **MongoDB** como parte de la asignatura de **Acceso a Datos** del ciclo formativo **Desarrollo de Aplicaciones Multiplataforma (DAM)**.

---

## Requisitos

- **Java 17** o superior
- **Maven**
- **MongoDB** (local o en la nube mediante [MongoDB Atlas](https://www.mongodb.com/atlas))
- IDE recomendado: IntelliJ IDEA, VS Code o Eclipse

---

## Instalación

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/tuusuario/taskmanager-api.git
   cd taskmanager-api

2. **Configura la conexión a MongoDB**:
Opcion A: MongoDB Local
    ```bash
     spring.data.mongodb.host=localhost
     spring.data.mongodb.port=27017
     spring.data.mongodb.database=taskmanager
Opcion B:MongoDB Atlas

     ```bash
          spring.data.mongodb.uri=mongodb+srv://tu_usuario:tu_contraseña@cluster0.xxxxx.mongodb.net/taskmanager

3. **Endpoint**:
   
