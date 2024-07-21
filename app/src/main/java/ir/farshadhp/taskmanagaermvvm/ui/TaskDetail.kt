package ir.farshadhp.taskmanagaermvvm.ui

import android.app.Application
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.farshadhp.taskmanagaermvvm.db.Task
import ir.farshadhp.taskmanagaermvvm.util.AndroidAlarmScheduler
import ir.farshadhp.taskmanagaermvvm.vm.DetailViewModel
import ir.farshadhp.taskmanagaermvvm.vm.DetailViewModelFactory
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun DetailScreen(
    selectedId: Long,
    scheduler: AndroidAlarmScheduler,
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
        descriptionText = state.description,
        onTimeTextChange = { viewModel.onTimeChange(it) },
        deadlineText = state.deadline,
        onDeadlineTextChange = { viewModel.onDeadlineChange(it) },
        onNavigate = { onNavigate() },
        onSaveTask = { viewModel.insert(it) },
        selectedId = state.selectId,
        scheduler
    )
}

@Composable
fun DetailScreenComponent(
    taskText: String,
    onTaskTextChange: (String) -> Unit,
    descriptionText: String,
    onTimeTextChange: (String) -> Unit,
    deadlineText: String,
    onDeadlineTextChange: (String) -> Unit,
    onNavigate: () -> Unit,
    onSaveTask: (Task) -> Unit,
    selectedId: Long,
    scheduler: AndroidAlarmScheduler
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
            value = descriptionText,
            onValueChange = { onTimeTextChange(it) },
            label = { Text(text = "Enter Desc") }
        )
        Spacer(modifier = Modifier.size(16.dp))
        OutlinedTextField(
            value = deadlineText,
            onValueChange = { onDeadlineTextChange(it) },
            label = { Text(text = "Enter remaining Days.Ex: 1", fontSize = 12.sp) }
        )
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = {
            val deadLineTemp = (LocalDateTime.now().plusDays(deadlineText.toLong()).atZone(ZoneId.systemDefault()).toEpochSecond() * 1000 ).toString()
//            val deadLineTemp = (LocalDateTime.now().plusSeconds(10).atZone(ZoneId.systemDefault())
//                .toEpochSecond() * 1000).toString()
            val task = if (isTaskEdit) Task(taskText, descriptionText, deadlineText, deadLineTemp)
            else Task(taskText, descriptionText, deadlineText, deadLineTemp, id = selectedId)
            onSaveTask(task)
            scheduler.schedule(task)
            onNavigate()
        }) {
            val text = if (isTaskEdit) "Save Task" else "Update task"
            Text(text = text)
        }


    }

}