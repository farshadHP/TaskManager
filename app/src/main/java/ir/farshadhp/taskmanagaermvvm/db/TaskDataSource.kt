package ir.farshadhp.taskmanagaermvvm.db

import kotlinx.coroutines.Dispatchers

class TaskDataSource(private val taskDao: TaskDao) {
    val selectAll = taskDao.selectAll()

    suspend fun insertTask(task: Task) {
        Dispatchers.IO.apply {
            taskDao.insert(task)
        }
    }

    suspend fun deleteTask(task: Task) {
        Dispatchers.IO.apply {
            taskDao.delete(task.id)
        }
    }

    suspend fun updateTask(isComplete: Boolean, id: Long) {
        Dispatchers.IO.apply {
            taskDao.updateTask(isComplete, id)
        }
    }


}