package cl.santos.animales;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Alarma extends AppCompatActivity {
    private Button btnSeleccionarFechaHora;
    private Button btnGuardarAlarma;
    private TextView textViewFechaSeleccionada;
    private TextView textViewHoraSeleccionada;
    private Calendar selectedDateTime = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);

        // Crear el canal de notificación
        createNotificationChannel();

        btnSeleccionarFechaHora = findViewById(R.id.btnSeleccionarFechaHora);
        btnGuardarAlarma = findViewById(R.id.btnGuardarAlarma);
        textViewFechaSeleccionada = findViewById(R.id.textViewFechaSeleccionada);
        textViewHoraSeleccionada = findViewById(R.id.textViewHoraSeleccionada);

        btnSeleccionarFechaHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarSelectorFechaHora();
            }
        });

        btnGuardarAlarma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                programarAlarma();
                Intent intent = new Intent(Alarma.this, Inicio.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Canal de Notificación";
            String description = "Descripción del canal";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("canal_id", name, importance);
            channel.setDescription(description);

            // Registrar el canal con el NotificationManager
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void mostrarSelectorFechaHora() {
        // Obtener la fecha y hora actuales
        int year = selectedDateTime.get(Calendar.YEAR);
        int month = selectedDateTime.get(Calendar.MONTH);
        int dayOfMonth = selectedDateTime.get(Calendar.DAY_OF_MONTH);
        int hourOfDay = selectedDateTime.get(Calendar.HOUR_OF_DAY);
        int minute = selectedDateTime.get(Calendar.MINUTE);

        // Mostrar un DatePickerDialog para seleccionar la fecha
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedDateTime.set(Calendar.YEAR, year);
                selectedDateTime.set(Calendar.MONTH, month);
                selectedDateTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Mostrar un TimePickerDialog para seleccionar la hora
                TimePickerDialog timePickerDialog = new TimePickerDialog(Alarma.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedDateTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        selectedDateTime.set(Calendar.MINUTE, minute);

                        // Actualizar los TextView con la fecha y hora seleccionadas
                        actualizarTextViewsFechaHora();
                    }
                }, hourOfDay, minute, true);

                timePickerDialog.show();
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }


    private void actualizarTextViewsFechaHora() {
         String fechaSeleccionada = android.text.format.DateFormat.format("dd-MM-yyyy", selectedDateTime).toString();
        String horaSeleccionada = android.text.format.DateFormat.format("HH:mm", selectedDateTime).toString();

         textViewFechaSeleccionada.setText("Fecha seleccionada: " + fechaSeleccionada);
        textViewHoraSeleccionada.setText("Hora seleccionada: " + horaSeleccionada);
    }


    private void programarAlarma() {
        // Obtener el servicio AlarmManager
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // Crear un Intent para la clase AlarmReceiver
        Intent intent = new Intent(this, AlarmReceiver.class);

        // Crear un PendingIntent para la alarma
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        // Configurar el calendario con la fecha y hora seleccionadas
        Calendar calendar = selectedDateTime;

        // Programar la alarma
        // Utilizar RTC_WAKEUP para que la alarma despierte el dispositivo incluso si está en modo de suspensión
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

}
