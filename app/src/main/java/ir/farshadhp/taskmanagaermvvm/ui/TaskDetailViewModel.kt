package ir.farshadhp.taskmanagaermvvm.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ir.farshadhp.taskmanagaermvvm.Graph
import ir.farshadhp.taskmanagaermvvm.db.Task
import ir.farshadhp.taskmanagaermvvm.db.TaskDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class DetailViewModel(
    private val taskDataSource: TaskDataSource = Graph.taskRepo,
    private val id: Long,
) : ViewModel() {
    private val taskText = MutableStateFlow("")
    private val taskTime = MutableStateFlow("")
    private val selectId = MutableStateFlow(-1L)

    private val _state = MutableStateFlow(DetailViewState())
    val state: StateFlow<DetailViewState>
        get() = _state

    init {
        viewModelScope.launch {
            combine(taskText, taskTime, selectId) { text, time, id ->
                DetailViewState(text, time, id)
            }.collect {
                _state.value = it
            }
        }
    }

    init {
        viewModelScope.launch {
            taskDataSource.selectAll.collect { task ->
                task.find {
                    it.id == selectId.value
                }.also {
                    selectId.value = it?.id ?: -1
                    if (selectId.value != -1L) {
                        taskText.value = it?.title ?: ""
                        taskTime.value = it?.time ?: ""
                    }
                }
            }
        }

    }

    fun onTextChange(newText: String) {
        taskText.value = newText
    }

    fun onTimeChange(newText: String) {
        taskTime.value = newText
    }

    fun insert(task: Task) = viewModelScope.launch {
        taskDataSource.insertTask(task)
    }

}

data class DetailViewState(
    val task: String = "",
    val time: String = "",
    val selectId: Long = -1L,
)

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val id: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(id = id) as T
        } else {
            throw IllegalArgumentException("unKnown view model class")
        }
    }
}