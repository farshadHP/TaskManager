package ir.farshadhp.taskmanagaermvvm.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.farshadhp.taskmanagaermvvm.Graph
import ir.farshadhp.taskmanagaermvvm.db.Task
import ir.farshadhp.taskmanagaermvvm.db.TaskDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class TaskListViewModel(private val taskDataSource: TaskDataSource = Graph.taskRepo) : ViewModel() {
    private val _state = MutableStateFlow(HomeViewState())
    val state: StateFlow<HomeViewState>
        get() = _state

    val taskList = taskDataSource.selectAll
    val selected = MutableStateFlow(_state.value.selected)

    init {
        viewModelScope.launch {
            combine(taskList, selected) { taskList: List<Task>, selected: Boolean ->
                HomeViewState(taskList, selected)
            }.collect {
                _state.value = it
            }
        }
    }

    fun updateTask(selected: Boolean, id: Long) = viewModelScope.launch {
        taskDataSource.updateTask(selected, id)
    }

    fun delete(task: Task) = viewModelScope.launch {
        taskDataSource.deleteTask(task)
    }

}

data class HomeViewState(
    val taskList: List<Task> = emptyList(),
    val selected: Boolean = false,
)