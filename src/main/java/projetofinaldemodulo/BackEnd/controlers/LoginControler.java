package projetofinaldemodulo.BackEnd.controlers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projetofinaldemodulo.BackEnd.Dtos.ErrorData;
import projetofinaldemodulo.BackEnd.Dtos.RequestLogin;
import projetofinaldemodulo.BackEnd.dataBase.DataBase;

@RestController
@RequestMapping("/login")
public class LoginControler {
    @PostMapping
    public ResponseEntity loginUser(@RequestBody @Valid RequestLogin login){
        try {
            var user = DataBase.getEmail(login.email());
            if(user.getPassword().equals(login.password())){
                return ResponseEntity.ok().body(login);
            }
            return ResponseEntity.badRequest().body(new ErrorData("E-mail ou senha inválidos."));

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorData("Login inválido."));
        }
    }
}

