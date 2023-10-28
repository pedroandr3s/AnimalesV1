package cl.santos.animales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Inicio extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        ImageButton btnMapa = findViewById(R.id.Mapa);

        btnMapa.setOnClickListener(v -> {
            // Crear un Intent para iniciar la actividad DatosOtroActivity
            Intent intent = new Intent(Inicio.this, MapsActivity.class);
            startActivity(intent);
        });
    }
 }
