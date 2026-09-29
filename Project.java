package com.devsenior.projects;

import com.devsenior.projects.exceptions.ArrayFullException;
import com.devsenior.projects.exceptions.IdAlreadyExistsException;
import com.devsenior.projects.exceptions.InvalidDataException;
import com.devsenior.projects.exceptions.TaskAlreadyCompletedException;
import com.devsenior.projects.exceptions.TaskNotFoundException;

import java.util.Objects;

public class Project {

    private String projectId;
    private String name;
    private String description;
    private Task[] tasks;
    private int taskCount;

    public Project(String projectId, String name, String description) {

        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.tasks = new Task[5];
        this.taskCount = 0;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Task[] getTasks() {
        return tasks;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void addTask(Task task)
            throws ArrayFullException {

        if (task == null) {
            throw new InvalidDataException(
                    "La tarea no puede ser nula."
            );
        }

        if (task.getTaskId() == null ||
                task.getTaskId().trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID de la tarea es obligatorio."
            );
        }

        if (task.getDescription() == null ||
                task.getDescription().trim().isEmpty()) {

            throw new InvalidDataException(
                    "La descripción de la tarea es obligatoria."
            );
        }

        if (task.getPriority() == null ||
                task.getPriority().trim().isEmpty()) {

            throw new InvalidDataException(
                    "La prioridad de la tarea es obligatoria."
            );
        }

        if (taskCount >= tasks.length) {

            throw new ArrayFullException(
                    "No se pueden agregar más tareas al proyecto."
            );
        }

        for (int i = 0; i < taskCount; i++) {

            if (tasks[i].getTaskId()
                    .equals(task.getTaskId())) {

                throw new IdAlreadyExistsException(
                        "Ya existe una tarea con el ID: "
                                + task.getTaskId()
                );
            }
        }

        tasks[taskCount] = task;
        taskCount++;
    }

    public void markTaskCompleted(String taskId)
            throws TaskNotFoundException,
            TaskAlreadyCompletedException {

        if (taskId == null ||
                taskId.trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID de la tarea es obligatorio."
            );
        }

        Task task = findTask(taskId);

        if (task.isCompleted()) {

            throw new TaskAlreadyCompletedException(
                    "La tarea ya está completada."
            );
        }

        task.setCompleted(true);
    }

    public Task findTask(String taskId)
            throws TaskNotFoundException {

        if (taskId == null ||
                taskId.trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID de la tarea es obligatorio."
            );
        }

        for (int i = 0; i < taskCount; i++) {

            if (tasks[i].getTaskId()
                    .equals(taskId)) {

                return tasks[i];
            }
        }

        throw new TaskNotFoundException(
                "No se encontró la tarea con ID: "
                        + taskId
        );
    }

    public void listTasks() {

        if (taskCount == 0) {

            System.out.println(
                    "El proyecto no tiene tareas."
            );

            return;
        }

        for (int i = 0; i < taskCount; i++) {

            System.out.println(
                    "  " + tasks[i]
            );
        }
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Project)) {
            return false;
        }

        Project project = (Project) o;

        return Objects.equals(
                projectId,
                project.projectId
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

    @Override
    public String toString() {

        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", taskCount=" + taskCount +
                '}';
    }
}