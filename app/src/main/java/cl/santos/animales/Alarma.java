package cl.santos.animales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;


public class Alarma extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma);
        Button siguiente = findViewById(R.id.btnAgregar);

        siguiente.setOnClickListener(view -> {
            Intent intent = new Intent(Alarma.this, Inicio.class);
            startActivity(intent);
            finish();
        });
    }}