package projetofinaldemodulo.BackEnd.dataBase;

import projetofinaldemodulo.BackEnd.Dtos.TasksDetail;
import projetofinaldemodulo.BackEnd.models.Task;
import projetofinaldemodulo.BackEnd.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class DataBase {
    private static ArrayList<User> users = new ArrayList<>();

    public static void addUser(User user){
        if(user.getEmail() == null){
            throw new RuntimeException("Usuário inválido");
        }

        users.add(user);
    }

    public static boolean userExitsByEmail(String email){
        var userFiltered = users.stream().filter((user) -> user.getEmail().equalsIgnoreCase(email)).findAny();
        return userFiltered.isPresent();
    }

    public static boolean passwordUser(String password, String repassword){
        if(password.equals(repassword) ){
            return true;
        }else{
            return false;
        }
    }

    public static User getUserByEmail(String email){
        var userFiltered = users.stream().filter(user -> user.getEmail().equals(email)).findAny();

        if(userFiltered.isEmpty()){
            return null;
        }

        return userFiltered.get();
    }

    public static List<TasksDetail> getAllTasks(String email){
        var user = getUserByEmail(email);
        var tasks = user.getTasks();

        if(tasks.isEmpty()){
            return null;
        }

        return tasks.stream().map(TasksDetail::new).toList();
    }

    public static Task getTaskByID(UUID idTask, String email){
        var user = getUserByEmail(email);
        var taskFiltered = user.getTasks().stream().filter(id -> id.getId().equals(idTask)).findAny();

        if(taskFiltered.isEmpty()) {
            return null;
        }

        return taskFiltered.get();
    }

    public static User getEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findFirst().orElseThrow();
    }

}




