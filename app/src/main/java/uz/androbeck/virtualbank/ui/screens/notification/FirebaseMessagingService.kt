package uz.androbeck.virtualbank.ui.screens.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.domain.ui_models.notification.NotificationUIModel
import uz.androbeck.virtualbank.ui.MainActivity
import uz.androbeck.virtualbank.utils.Constants

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {
            showNotification(it.title, it.body)
        }
        val title = remoteMessage.notification?.title
        val description = remoteMessage.notification?.body
        val time = System.currentTimeMillis()
        val image = remoteMessage.notification?.imageUrl.toString()
        val navigationType = 0
        val notification = NotificationUIModel(
            image = image,
            time = time,
            title = title,
            description = description,
            navigationType = navigationType
        )
        val database =
            FirebaseDatabase.getInstance(Constants.Firebase.FIREBASE_REAL_TIME_DATABASE_URL).reference
        val notificationRef = database.child(Constants.Firebase.FIREBASE_NOTIFICATION).push()

        notificationRef.setValue(notification)
            .addOnSuccessListener {
                Log.d("Firebase", "Notification successfully saved.")
            }
            .addOnFailureListener {
                Log.e("Firebase", "Error saving notification")
            }
    }

    private fun showNotification(title: String?, body: String?) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.Firebase.CHANNEL_ID,
                Constants.Firebase.DEFAULT_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, Constants.Firebase.CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.virtual_bank_logo)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(0, notification)
    }
}