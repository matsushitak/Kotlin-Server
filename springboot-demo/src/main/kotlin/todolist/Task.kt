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
        val id: Int = 1,
        val content: String = "",
        val done: Int = 0
)