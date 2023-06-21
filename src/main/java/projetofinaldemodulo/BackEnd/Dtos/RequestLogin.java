package projetofinaldemodulo.BackEnd.Dtos;

import jakarta.validation.constraints.NotBlank;

public record RequestLogin(@NotBlank String email, @NotBlank String password) {

}
