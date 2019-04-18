package todolist

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "tasks")
data class Task(
        @Id
        @GeneratedValue
        val id: Long,
        val content: String,
        val done: Boolean
)