package cl.santos.animales;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.app.Notification;
import android.app.NotificationManager;
import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        mostrarNotificacion(context);
    }

    private void mostrarNotificacion(Context context) {
        // Aquí puedes personalizar la notificación según tus necesidades
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "canal_id")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("¡Es hora!")
                .setContentText("Tu alarma ha sonado.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }
}
