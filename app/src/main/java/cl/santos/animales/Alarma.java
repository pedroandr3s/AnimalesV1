package cl.santos.animales;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Alarma extends AppCompatActivity {
    private AlarmManager alarmManager;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        timePicker = findViewById(R.id.timePicker);
        Button btnAgregar = findViewById(R.id.btnAgregar);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        btnAgregar.setOnClickListener(v -> {
            setAlarm();
            Intent intent = new Intent(Alarma.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void setAlarm() {
        int hour, minute;

        if (android.os.Build.VERSION.SDK_INT >= 26) {
            hour = timePicker.getHour();
            minute = timePicker.getMinute();
        } else {
            hour = timePicker.getCurrentHour();
            minute = timePicker.getCurrentMinute();
        }


        long alarmTime = getAlarmTime(hour, minute);

        // Crear un intent para el servicio en segundo plano
        Intent serviceIntent = new Intent(this, AlarmService.class);
        serviceIntent.putExtra("hour", hour);
        serviceIntent.putExtra("minute", minute);

        // Programar la alarma
        alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime, PendingIntent.getService(this, 0, serviceIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        Toast.makeText(this, "Alarma configurada", Toast.LENGTH_SHORT).show();
    }

    private long getAlarmTime(int hour, int minute) {
        long currentTime = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        if (calendar.getTimeInMillis() <= currentTime) {
            // Si la hora seleccionada ya ha pasado, programar la alarma para el día siguiente
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        return calendar.getTimeInMillis();
    }
}
