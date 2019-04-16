package todolist

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity(name = "tasks")
data class Task(
        @Id
        @GeneratedValue
        val id: Int,
        val content: String,
        val done: Boolean
)