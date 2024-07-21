package ir.farshadhp.taskmanagaermvvm

import android.app.Application

class TaskApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}