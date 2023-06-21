package projetofinaldemodulo.BackEnd.models;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.IdGenerator;
import projetofinaldemodulo.BackEnd.Dtos.CreateUser;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class User {

    private String email;
    private String password;
    private List<Task> tasks;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        tasks = new ArrayList<Task>();
    }
}






