package com.devsenior.projects;

import com.devsenior.projects.exceptions.ArrayFullException;
import com.devsenior.projects.exceptions.IdAlreadyExistsException;
import com.devsenior.projects.exceptions.InvalidDataException;
import com.devsenior.projects.exceptions.ProjectNotFoundException;
import com.devsenior.projects.exceptions.TaskAlreadyCompletedException;
import com.devsenior.projects.exceptions.TaskNotFoundException;

public class ProjectManager {

    private Project[] projects;
    private int projectCount;

    public ProjectManager() {

        this.projects = new Project[5];
        this.projectCount = 0;
    }

    public void addProject(Project project)
            throws ArrayFullException {

        if (project == null) {

            throw new InvalidDataException(
                    "El proyecto no puede ser nulo."
            );
        }

        if (project.getProjectId() == null ||
                project.getProjectId().trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID del proyecto es obligatorio."
            );
        }

        if (project.getName() == null ||
                project.getName().trim().isEmpty()) {

            throw new InvalidDataException(
                    "El nombre del proyecto es obligatorio."
            );
        }

        if (project.getDescription() == null ||
                project.getDescription().trim().isEmpty()) {

            throw new InvalidDataException(
                    "La descripción del proyecto es obligatoria."
            );
        }

        if (projectCount >= projects.length) {

            throw new ArrayFullException(
                    "No se pueden agregar más proyectos."
            );
        }

        for (int i = 0; i < projectCount; i++) {

            if (projects[i].getProjectId()
                    .equals(project.getProjectId())) {

                throw new IdAlreadyExistsException(
                        "Ya existe un proyecto con el ID: "
                                + project.getProjectId()
                );
            }
        }

        projects[projectCount] = project;
        projectCount++;
    }

    public Project findProject(String projectId)
            throws ProjectNotFoundException {

        if (projectId == null ||
                projectId.trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID del proyecto es obligatorio."
            );
        }

        for (int i = 0; i < projectCount; i++) {

            if (projects[i].getProjectId()
                    .equals(projectId)) {

                return projects[i];
            }
        }

        throw new ProjectNotFoundException(
                "No se encontró el proyecto con ID: "
                        + projectId
        );
    }

    public void addTaskToProject(
            String projectId,
            Task task
    ) throws ProjectNotFoundException,
            ArrayFullException {

        if (projectId == null ||
                projectId.trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID del proyecto es obligatorio."
            );
        }

        if (task == null) {

            throw new InvalidDataException(
                    "La tarea no puede ser nula."
            );
        }

        Project project = findProject(projectId);

        project.addTask(task);
    }

    public void markTaskCompletedInProject(
            String projectId,
            String taskId
    ) throws ProjectNotFoundException,
            TaskNotFoundException,
            TaskAlreadyCompletedException {

        if (projectId == null ||
                projectId.trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID del proyecto es obligatorio."
            );
        }

        if (taskId == null ||
                taskId.trim().isEmpty()) {

            throw new InvalidDataException(
                    "El ID de la tarea es obligatorio."
            );
        }

        Project project = findProject(projectId);

        project.markTaskCompleted(taskId);
    }

    public void listAllProjects() {

        System.out.println("\n--- LISTA DE PROYECTOS ---");

        for (int i = 0; i < projectCount; i++) {

            System.out.println(
                    "\n" + projects[i]
            );

            projects[i].listTasks();
        }
    }
}