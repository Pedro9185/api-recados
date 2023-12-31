package projetofinaldemodulo.BackEnd.controlers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projetofinaldemodulo.BackEnd.Dtos.AddTask;
import projetofinaldemodulo.BackEnd.Dtos.ErrorData;
import projetofinaldemodulo.BackEnd.Dtos.UpdateTask;
import projetofinaldemodulo.BackEnd.dataBase.DataBase;
import projetofinaldemodulo.BackEnd.models.Task;

import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskControler {

    @PostMapping("/{email}/tasksUser")
    public ResponseEntity addTasks(@PathVariable String email, @RequestBody @Valid AddTask newTask) {

        var user = DataBase.getUserByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }
        user.getTasks().add(new Task(newTask));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{email}/tasksUser")
    public ResponseEntity getTasks(@PathVariable String email, @RequestParam(required = false) String title, @RequestParam(required = false) boolean archived) {
        var tasks = DataBase.getAllTasks(email);
        var esseTemReatribuicao = DataBase.getEmail(email).getTasks();

        if (tasks == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Task não localizada."));
        }

        if (title != null) {
            esseTemReatribuicao = esseTemReatribuicao.stream().filter(t -> t.getTitle().contains((title))).toList();
            return ResponseEntity.ok().body(esseTemReatribuicao);
        }

        if (archived) {
            esseTemReatribuicao = esseTemReatribuicao.stream().filter(a -> a.getArchive().equals(true)).toList();
            return ResponseEntity.ok().body(esseTemReatribuicao);
        }
        return ResponseEntity.ok().body(tasks);
    }

    @DeleteMapping("/{email}/tasksUser/{idTask}")
    public ResponseEntity deleteTasks(@PathVariable String email, @PathVariable UUID idTask) {
        var user = DataBase.getUserByEmail(email);
        if (user == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Usuário não localizado."));
        }
        var taskId = DataBase.getTaskByID(idTask, email);

        user.getTasks().remove(taskId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email}/tasksUser/{idTask}")
    public ResponseEntity updateTask(@PathVariable String email, @PathVariable UUID idTask, @RequestBody UpdateTask taskUpdated) {
        var user = DataBase.getUserByEmail(email);

        var taskUpdateFilter = user.getTasks().stream().filter(t -> t.getId().equals(idTask)).findAny();

        if (taskUpdateFilter == null) {
            return ResponseEntity.badRequest().body(new ErrorData("Recado não encontrado."));
        }

        taskUpdateFilter.get().UpdateTask(taskUpdated);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{email}/tasksUser/{idTask}/archive")
    public ResponseEntity archiveTask(@PathVariable String email, @PathVariable UUID idTask) {

        var task = DataBase.getTaskByID(idTask, email);
        var archived = task.getArchive();

        task.setArchive(!archived);
        return ResponseEntity.ok().body(task.getArchive());
    }
}
