package presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import domain.RequestStates
import domain.ToDoTask
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.compose_multiplatform
import kotlinproject.composeapp.generated.resources.delete
import kotlinproject.composeapp.generated.resources.star
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


/**
 * @Author: Abdul Rehman
 * @Date: 26/04/2024.
 */

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TaskView(
    task: ToDoTask,
    showActive: Boolean = true,
    onSelect: (ToDoTask) -> Unit,
    onComplete: (ToDoTask, Boolean) -> Unit,
    onFavorite: (ToDoTask, Boolean) -> Unit,
    onDelete: (ToDoTask) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable {
                if (showActive) {
                    onSelect(task)
                } else {
                    onDelete(task)
                }
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = task.completed,
                onCheckedChange = { onComplete(task, !task.completed) })
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                modifier = Modifier.alpha(if (showActive) 1f else 0.5f), text = task.title,
                fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                textDecoration = if (showActive) TextDecoration.None else TextDecoration.LineThrough
            )
        }

        IconButton(
            onClick = {
                if (showActive) onFavorite(task, !task.favourite)
                else onDelete(task)
            }
        ) {

            Icon(
                painter = painterResource(
                    if (showActive) {
                        Res.drawable.star
                    } else {
                        Res.drawable.delete
                    }
                ),
                contentDescription = "Favourite Icon",
                tint = if (task.favourite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.38f
                )
            )
        }
    }
}