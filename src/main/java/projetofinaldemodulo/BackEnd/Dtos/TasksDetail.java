package projetofinaldemodulo.BackEnd.Dtos;

import projetofinaldemodulo.BackEnd.models.Task;

import java.util.UUID;

public record TasksDetail(
        UUID id,
        String title,
        String description,
        Boolean archive
) {
    public TasksDetail(Task task) {
        this(task.getId(), task.getTitle(), task.getDescription(), task.getArchive());
    }

}