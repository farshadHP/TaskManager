package ir.farshadhp.taskmanagaermvvm.util

import ir.farshadhp.taskmanagaermvvm.db.Task

interface AlarmScheduler {
    fun schedule(item: Task)
    fun cancel(item: Task)
}