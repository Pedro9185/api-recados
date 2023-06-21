package projetofinaldemodulo.BackEnd.controlers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetofinaldemodulo.BackEnd.Dtos.CreateUser;
import projetofinaldemodulo.BackEnd.Dtos.ErrorData;
import projetofinaldemodulo.BackEnd.Dtos.UserDetail;
import projetofinaldemodulo.BackEnd.dataBase.DataBase;
import projetofinaldemodulo.BackEnd.models.User;

import java.util.stream.Stream;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public ResponseEntity createUser(@RequestBody CreateUser data) {
        if (DataBase.userExitsByEmail(data.email())) {
            return ResponseEntity.badRequest().body(new ErrorData("Email já cadastrado"));
        }

        if (!DataBase.passwordUser(data.password(), data.repassword())) {
            return ResponseEntity.badRequest().body(new ErrorData("As senhas devem ser iguais."));
        }

        var user = new User(
                data.email(),
                data.password()
        );

        DataBase.addUser(user);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}")
    public ResponseEntity getUser(@PathVariable String email) {
        var user = DataBase.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body(new ErrorData("User não localizado"));
        }
        var userDetails = new UserDetail(user);

        return ResponseEntity.ok(userDetails);
    }
}
