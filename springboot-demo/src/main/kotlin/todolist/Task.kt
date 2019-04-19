package todolist

import javax.persistence.*

@Entity
@Table(name = "tasks")
data class Task(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int = 1,
        val content: String = "",
        val done: Boolean = false
)