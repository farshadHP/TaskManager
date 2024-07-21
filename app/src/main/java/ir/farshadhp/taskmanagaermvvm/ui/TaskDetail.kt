package ir.farshadhp.taskmanagaermvvm.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.farshadhp.taskmanagaermvvm.db.Task

@Composable
fun DetailScreen(
    selectedId: Long,
    onNavigate: () -> Unit,
) {
    val viewModel = viewModel(
        DetailViewModel::class.java,
        factory = DetailViewModelFactory(selectedId)
    )
    val state by viewModel.state.collectAsState()
    DetailScreenComponent(
        taskText = state.task,
        onTaskTextChange = { viewModel.onTextChange(it) },
        timeText = state.time,
        onTimeTextChange = { viewModel.onTimeChange(it) },
        onNavigate = { onNavigate() },
        onSaveTask = { viewModel.insert(it) },
        selectedId = state.selectId
    )
}

@Composable
fun DetailScreenComponent(
    taskText: String,
    onTaskTextChange: (String) -> Unit,
    timeText: String,
    onTimeTextChange: (String) -> Unit,
    onNavigate: () -> Unit,
    onSaveTask: (Task) -> Unit,
    selectedId: Long,
) {
    val isTaskEdit = selectedId == -1L
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = taskText,
            onValueChange = { onTaskTextChange(it) },
            label = { Text(text = "Enter Task") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = timeText,
            onValueChange = { onTimeTextChange(it) },
            label = { Text(text = "Enter Time") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            val task = if (isTaskEdit) Task(taskText, timeText)
            else Task(taskText, timeText, id = selectedId)
            onSaveTask(task)
            onNavigate()
        }) {
            val text = if (isTaskEdit) "Save Task" else "Update task"
            Text(text = text)
        }


    }

}