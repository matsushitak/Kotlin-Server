package todolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("tasks")
class TaskController(@Autowired private val service: TaskService) {

    @GetMapping("")
    fun getTasks(): List<Task> {
        var tasks = service.findAll()
        println(tasks)
        return tasks
    }

    @GetMapping("{id}")
    fun getTask(@PathVariable("id") id: Long): Task {
        return service.findById(id)
    }

    @PostMapping("")
    fun createTask(@Validated @RequestBody task: Task) {
        service.create(task)
    }

    @DeleteMapping("{id}")
    fun deleteTask(@PathVariable("id") id: Long) {
        service.deleteById(id)
    }
}