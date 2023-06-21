package projetofinaldemodulo.BackEnd.Dtos;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public record CreateUser(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String password,
        @NotBlank
        String repassword
)
{}
