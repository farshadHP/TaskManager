package ir.farshadhp.taskmanagaermvvm.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun selectAll(): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("Delete From task WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("DELETE FROM task")
    suspend fun deleteAllTask()

    @Query("UPDATE task SET isComplete = :isComplete WHERE id = :id")
    suspend fun updateTask(isComplete: Boolean, id: Long)

}