package todolist

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Table(name = "tasks")
data class Task(
        @Id
        @GeneratedValue
        val id: Int,
        val content: String,
        val done: Boolean
)