package todolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TaskService(@Autowired private val repository: TaskRepository) {

    fun findAll(): List<Task> {
        return repository.findAll()
    }

    fun findById(id: Long): Task {
        return repository.findById(id).get()
    }

    fun create(task: Task) {
        repository.save(task)
    }

    fun deleteById(id: Long) {
        repository.deleteById(id)
    }
}