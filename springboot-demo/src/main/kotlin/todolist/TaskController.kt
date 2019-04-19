package todolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tasks")
class TaskController(@Autowired private val service: TaskService) {

    @GetMapping("")
    fun getTasks(): List<Task> {
        return service.findAll()
    }

    @GetMapping("{id}")
    fun getTask(@PathVariable("id") id: Int): Task {
        return service.findById(id)
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    fun createTask(@RequestBody task: Task): Task {
        println(task)
        return service.create(task)
    }

    @DeleteMapping("{id}")
    fun deleteTask(@PathVariable("id") id: Int) {
        service.deleteById(id)
    }
}