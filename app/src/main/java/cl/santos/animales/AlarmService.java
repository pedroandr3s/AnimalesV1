package cl.santos.animales;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
import java.util.Calendar;

public class AlarmService extends IntentService {
    public AlarmService() {
        super("AlarmService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            // Obtén la hora y minuto de la alarma del intent
            int hour = extras.getInt("hour");
            int minute = extras.getInt("minute");

            // Obtén la hora y minuto actuales
            Calendar now = Calendar.getInstance();
            int currentHour = now.get(Calendar.HOUR_OF_DAY);
            int currentMinute = now.get(Calendar.MINUTE);

            // Comprueba si la alarma debe activarse
            if (hour == currentHour && minute == currentMinute) {
                // Crea una notificación cuando la alarma se active
                NotificationHelper notificationHelper = new NotificationHelper(this);
                notificationHelper.createNotification("Hora de alimentar a tu perro", "No olvides alimentar a tu mascota.", new Intent(this, Alarma.class));
            }
        }
    }
}
