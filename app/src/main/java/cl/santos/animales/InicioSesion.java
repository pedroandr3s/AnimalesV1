package cl.santos.animales;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        Button botoninicio = findViewById(R.id.btninicio);
 botoninicio.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Crear un Intent para iniciar la actividad DatosOtroActivity
            Intent intent = new Intent(InicioSesion.this, MainActivity.class);
            startActivity(intent);
        }
    });
    }
}