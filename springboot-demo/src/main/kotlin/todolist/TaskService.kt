package todolist

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

interface TaskService {
    fun findAll(): List<Task>
    fun findById(id: Long): Task
    fun create(task: Task)
    fun deleteById(id: Long)
}

@Service
@Transactional
class TaskServiceImpl(@Autowired private val repository: TaskRepository) : TaskService {

    override fun findAll(): List<Task> {
        return repository.findAll()
    }

    override fun findById(id: Long): Task {
        return repository.findById(id).get()
    }

    override fun create(task: Task) {
        repository.save(task)
    }

    override fun deleteById(id: Long) {
        repository.deleteById(id)
    }
}