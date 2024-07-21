package ir.farshadhp.taskmanagaermvvm.util


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import ir.farshadhp.taskmanagaermvvm.MainActivity


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val message = intent?.getStringExtra("MESSAGE") ?: return
//        Log.e("t", "Alarm is $message")

//        val emptyIntent = Intent()
//        val pendingIntent =
//            PendingIntent.getActivity(context, 1, emptyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
//        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context!!)
//            .setSmallIcon(R.drawable.baseline_access_time_24)
//            .setContentTitle("My notification")
//            .setContentText("Hello World!")
//            .setContentIntent(pendingIntent)
//
//        mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
////        val notificationManager : NotificationManager? =
////        notificationManager?.notify(1, mBuilder.build())
//
//        val notificationManager =
//            getSystemService(context, javaClass) as NotificationManager?
//        notificationManager!!.notify(1, mBuilder.build())

//        val intent: Intent = Intent(context, MainActivity::class.java)
//        val contentIntent =
//            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
//
//        val b = NotificationCompat.Builder(context!!)
//
//        b.setAutoCancel(true)
//            .setDefaults(Notification.DEFAULT_ALL)
//            .setWhen(System.currentTimeMillis())
//            .setSmallIcon(R.drawable.baseline_access_time_24)
//            .setTicker("Hearty365")
//            .setContentTitle("Default notification")
//            .setContentText("Lorem ipsum dolor sit amet, consectetur adipiscing elit.")
//            .setDefaults(Notification.DEFAULT_LIGHTS or Notification.DEFAULT_SOUND)
//            .setContentIntent(contentIntent)
//            .setContentInfo("Info")
//
//
//        (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager ).notify(1, b.build())

        val intent = Intent(
            context,
            MainActivity::class.java
        )
        val CHANNEL_ID = "MYCHANNEL"
        val notificationChannel =
            NotificationChannel(CHANNEL_ID, "name", NotificationManager.IMPORTANCE_LOW)
        notificationChannel.vibrationPattern = longArrayOf( 0, 100 )
        notificationChannel.enableVibration(true)
        val pendingIntent = PendingIntent.getActivity(context, 1, intent,
            PendingIntent.FLAG_IMMUTABLE)
        val notification = Notification.Builder(context, CHANNEL_ID)
            .setContentText("Task Deadline is coming...")
            .setContentTitle("Task Name: $message" )
            .setContentIntent(pendingIntent)
            .addAction(android.R.drawable.sym_action_chat, "Title", pendingIntent)
            .setChannelId(CHANNEL_ID)
            .setSmallIcon(android.R.drawable.sym_action_chat)
            .build()

        val notificationManager =
            context!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1, notification)
    }
}