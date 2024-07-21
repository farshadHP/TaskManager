package ir.farshadhp.taskmanagaermvvm.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    val title: String,
    val time: String,
    val isComplete: Boolean = false,
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
)