package ir.farshadhp.taskmanagaermvvm.ui


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ir.farshadhp.taskmanagaermvvm.db.Task
import ir.farshadhp.taskmanagaermvvm.vm.TaskListViewModel

@Composable
fun HomeScreen(onNavigate: (Task?) -> Unit) {
    val viewModel = viewModel(TaskListViewModel::class.java)
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigate(null) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { innerPadding ->

        LazyColumn (modifier = Modifier.padding(innerPadding)) {
            itemsIndexed(state.taskList) { _: Int, task: Task ->
                TaskItem(
                    task = task,
                    onChecked = { viewModel.updateTask(it, task.id) },
                    onDelete = { viewModel.delete(it) },
                    onNavigation = { onNavigate(it) }
                )
            }
        }

    }


}