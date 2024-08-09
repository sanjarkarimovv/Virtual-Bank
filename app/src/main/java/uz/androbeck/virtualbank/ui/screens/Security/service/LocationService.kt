package uz.androbeck.virtualbank.ui.screens.Security.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import uz.androbeck.virtualbank.R
import uz.androbeck.virtualbank.preferences.PreferencesProvider

@Suppress("UNREACHABLE_CODE")
class LocationService : Service() {
    private lateinit var locationManager: LocationManager
    private lateinit var locationListener: LocationListener
    lateinit var provider: PreferencesProvider
    override fun onCreate() {
        super.onCreate()
        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager
        locationListener =
            LocationListener { location -> provider.userLocation = location.toString() }
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
        val notification= createNotification(this)
        startForeground(1,notification)
        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,locationListener)
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        locationManager.removeUpdates(locationListener)
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }
    private fun createNotification(context: Context): Notification {
        val notificationManager=context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channelId="Channel1"
            val channelName="ChannelName"
            val importance=NotificationManager.IMPORTANCE_DEFAULT
            val chanel=NotificationChannel(channelId,channelName,importance)
            notificationManager.createNotificationChannel(chanel)
        }
        val builder=NotificationCompat.Builder(context,"channel1").setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Virtual Bank")
            .setContentText("Joylashuvdan foydalanilmoqda")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        notificationManager.notify(1,builder.build())
        return builder.build()
    }
}