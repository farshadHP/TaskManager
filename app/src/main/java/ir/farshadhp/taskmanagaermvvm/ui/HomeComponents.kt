package ir.farshadhp.taskmanagaermvvm.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.farshadhp.taskmanagaermvvm.db.Task

@Composable
fun TaskItem(
    task: Task,
    onChecked: (Boolean) -> Unit,
    onDelete: (Task) -> Unit,
    onNavigation: (Task) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable { onNavigation(task) },
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Checkbox(checked = task.isComplete, onCheckedChange = { onChecked(it) })
            Spacer(modifier = Modifier.size(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title)
                Spacer(modifier = Modifier.size(16.dp))

                Text(text = task.time)

            }
            Spacer(modifier = Modifier.size(16.dp))
            IconButton(onClick = { onDelete(task) }) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = null)
            }

        }


    }
}