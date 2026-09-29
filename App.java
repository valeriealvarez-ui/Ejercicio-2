package com.devsenior.projects;

import com.devsenior.projects.exceptions.ArrayFullException;
import com.devsenior.projects.exceptions.IdAlreadyExistsException;
import com.devsenior.projects.exceptions.InvalidDataException;
import com.devsenior.projects.exceptions.ProjectNotFoundException;
import com.devsenior.projects.exceptions.TaskAlreadyCompletedException;
import com.devsenior.projects.exceptions.TaskNotFoundException;

public class App {

    public static void main(String[] args) {

        ProjectManager manager = new ProjectManager();

        System.out.println("--- CASOS EXITOSOS ---");

        try {

            Project project1 = new Project(
                    "P001",
                    "Sistema Web",
                    "Desarrollo de una aplicación web"
            );

            Project project2 = new Project(
                    "P002",
                    "Aplicación Móvil",
                    "Desarrollo de una aplicación móvil"
            );

            manager.addProject(project1);
            manager.addProject(project2);

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T001",
                            "Diseñar la interfaz",
                            "HIGH"
                    )
            );

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T002",
                            "Crear la base de datos",
                            "HIGH"
                    )
            );

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T003",
                            "Programar el login",
                            "MEDIUM"
                    )
            );

            manager.addTaskToProject(
                    "P002",
                    new Task(
                            "T001",
                            "Diseñar pantalla principal",
                            "HIGH"
                    )
            );

            manager.addTaskToProject(
                    "P002",
                    new Task(
                            "T002",
                            "Crear menú",
                            "LOW"
                    )
            );

            System.out.println("\nTareas agregadas correctamente.");

            manager.markTaskCompletedInProject(
                    "P001",
                    "T001"
            );

            System.out.println(
                    "Tarea T001 del proyecto P001 completada."
            );

            Project proyectoEncontrado =
                    manager.findProject("P001");

            System.out.println(
                    "\nProyecto encontrado: "
                            + proyectoEncontrado
            );

            Task tareaEncontrada =
                    proyectoEncontrado.findTask("T002");

            System.out.println(
                    "Tarea encontrada: "
                            + tareaEncontrada
            );

            manager.listAllProjects();

        } catch (Exception e) {

            System.err.println(
                    "Error en escenario exitoso: "
                            + e.getMessage()
            );
        }


        System.out.println("\n--- CASOS DE ERROR ---");


        // 1. Proyecto con datos inválidos
        try {

            System.out.println(
                    "\nIntentando agregar proyecto con nombre vacío..."
            );

            Project proyectoInvalido =
                    new Project(
                            "P003",
                            "",
                            "Descripción"
                    );

            manager.addProject(proyectoInvalido);

        } catch (InvalidDataException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ArrayFullException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 2. Proyecto con ID duplicado
        try {

            System.out.println(
                    "\nIntentando agregar proyecto P001 nuevamente..."
            );

            Project proyectoDuplicado =
                    new Project(
                            "P001",
                            "Otro Proyecto",
                            "Otro proyecto"
                    );

            manager.addProject(proyectoDuplicado);

        } catch (IdAlreadyExistsException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ArrayFullException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 3. Llenar el array de proyectos
        try {

            System.out.println(
                    "\nAgregando proyectos hasta llenar el array..."
            );

            manager.addProject(
                    new Project(
                            "P003",
                            "Proyecto 3",
                            "Descripción 3"
                    )
            );

            manager.addProject(
                    new Project(
                            "P004",
                            "Proyecto 4",
                            "Descripción 4"
                    )
            );

            manager.addProject(
                    new Project(
                            "P005",
                            "Proyecto 5",
                            "Descripción 5"
                    )
            );

            System.out.println(
                    "Intentando agregar un sexto proyecto..."
            );

            manager.addProject(
                    new Project(
                            "P006",
                            "Proyecto 6",
                            "Descripción 6"
                    )
            );

        } catch (ArrayFullException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 4. Agregar tarea a proyecto inexistente
        try {

            System.out.println(
                    "\nIntentando agregar tarea al proyecto P999..."
            );

            manager.addTaskToProject(
                    "P999",
                    new Task(
                            "T999",
                            "Tarea inexistente",
                            "LOW"
                    )
            );

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ArrayFullException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 5. Tarea con datos inválidos
        try {

            System.out.println(
                    "\nIntentando agregar tarea con descripción nula..."
            );

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T004",
                            null,
                            "HIGH"
                    )
            );

        } catch (InvalidDataException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ArrayFullException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 6. Tarea con ID duplicado
        try {

            System.out.println(
                    "\nIntentando agregar T002 nuevamente en P001..."
            );

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T002",
                            "Otra tarea",
                            "LOW"
                    )
            );

        } catch (IdAlreadyExistsException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ArrayFullException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 7. Llenar array interno de tareas
        try {

            System.out.println(
                    "\nLlenando las tareas del proyecto P001..."
            );

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T004",
                            "Crear backend",
                            "MEDIUM"
                    )
            );

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T005",
                            "Realizar pruebas",
                            "LOW"
                    )
            );

            System.out.println(
                    "Intentando agregar una sexta tarea..."
            );

            manager.addTaskToProject(
                    "P001",
                    new Task(
                            "T006",
                            "Tarea adicional",
                            "LOW"
                    )
            );

        } catch (ArrayFullException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 8. Completar tarea de proyecto inexistente
        try {

            System.out.println(
                    "\nIntentando completar tarea en P999..."
            );

            manager.markTaskCompletedInProject(
                    "P999",
                    "T001"
            );

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (TaskNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (TaskAlreadyCompletedException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 9. Completar tarea que no existe
        try {

            System.out.println(
                    "\nIntentando completar T999 en P001..."
            );

            manager.markTaskCompletedInProject(
                    "P001",
                    "T999"
            );

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (TaskNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (TaskAlreadyCompletedException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 10. Completar una tarea que ya está completada
        try {

            System.out.println(
                    "\nIntentando completar T001 nuevamente..."
            );

            manager.markTaskCompletedInProject(
                    "P001",
                    "T001"
            );

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (TaskNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (TaskAlreadyCompletedException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 11. Buscar proyecto inexistente
        try {

            System.out.println(
                    "\nIntentando buscar proyecto P999..."
            );

            manager.findProject("P999");

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        // 12. Buscar tarea inexistente
        try {

            System.out.println(
                    "\nIntentando buscar tarea T999..."
            );

            manager.findProject("P001")
                    .findTask("T999");

        } catch (ProjectNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );

        } catch (TaskNotFoundException e) {

            System.err.println(
                    "CAPTURA DE ERROR: "
                            + e.getMessage()
            );
        }


        System.out.println(
                "\n--- FIN DE LAS PRUEBAS ---"
        );

        manager.listAllProjects();
    }
}