package cl.santos.animales;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encuentra el ImageButton con ID "otro"
        ImageButton botonOtro = findViewById(R.id.ibOtro);
        ImageButton botonPerro = findViewById(R.id.ibPerro);
        ImageButton botonGato = findViewById(R.id.ibGato);
        ImageButton botonCanario = findViewById(R.id.ibCanario);

        // Configura un OnClickListener para el ImageButton
        botonOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad DatosOtroActivity
                Intent intent = new Intent(MainActivity.this, DatosPerro.class);
                startActivity(intent);
            }
        });
        botonPerro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad DatosOtroActivity
                Intent intent = new Intent(MainActivity.this, DatosPerro.class);
                startActivity(intent);
            }
        });
        botonGato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad DatosOtroActivity
                Intent intent = new Intent(MainActivity.this, DatosCats.class);
                startActivity(intent);
            }
        });
        botonCanario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Crear un Intent para iniciar la actividad DatosOtroActivity
                Intent intent = new Intent(MainActivity.this, DatosPerro.class);
                startActivity(intent);
            }
        });

    }
}
