package ir.farshadhp.taskmanagaermvvm

import android.content.Context
import ir.farshadhp.taskmanagaermvvm.db.TaskDataSource
import ir.farshadhp.taskmanagaermvvm.db.TaskDatabase

object Graph {
    lateinit var database: TaskDatabase
        private set
    val taskRepo by lazy {
        TaskDataSource(database.taskDao())
    }

    fun provide(context: Context) {
        database = TaskDatabase.getDatabase(context)
    }
}