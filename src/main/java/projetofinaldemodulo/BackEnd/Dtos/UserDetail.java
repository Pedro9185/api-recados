package projetofinaldemodulo.BackEnd.Dtos;

import projetofinaldemodulo.BackEnd.models.Task;
import projetofinaldemodulo.BackEnd.models.User;

import java.util.List;

public record UserDetail(
        String email,
        String password,
        List<Task> task
) {
    public UserDetail(User U) {
        this(
                U.getEmail(),
                U.getPassword(),
                U.getTasks()
        );

    }


}

