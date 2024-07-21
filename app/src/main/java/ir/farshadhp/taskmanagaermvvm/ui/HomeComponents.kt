package ir.farshadhp.taskmanagaermvvm.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
//        Spacer(modifier = Modifier.size(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Checkbox(checked = task.isComplete, colors = CheckboxDefaults.colors(checkmarkColor = Color.White, checkedColor = Color.LightGray, uncheckedColor = Color.White), onCheckedChange = { onChecked(it) })
//            Spacer(modifier = Modifier.size(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = task.title, color = Color.White)
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = task.description, color = Color.LightGray)
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = task.deadline + " Days remaining", color = Color.White, fontSize = 12.sp)
            }
//            Spacer(modifier = Modifier.size(16.dp))
            IconButton(onClick = { onDelete(task) }) {
                Icon(imageVector = Icons.Default.Clear, tint = Color.White, contentDescription = null)
            }

        }


    }
}