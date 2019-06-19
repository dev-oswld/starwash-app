package com.equipo.starwash;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

//Clase que usamos para seleccionar el paquete
public class InicioDeTodo extends AppCompatActivity {
    private TextView Cliente;
    Spinner combito;
    TextView estado;
    Button BotonReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BotonReserva = (Button) findViewById(R.id.btn_reservahoy);
        setContentView(R.layout.activity_inicio_de_todo);
        Cliente = findViewById(R.id.txt_nombreCliente);

        combito = (Spinner)findViewById(R.id.spinnerPaquetes);
        estado = (TextView) findViewById(R.id.txt_infopaqueton);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.comboenxml,R.layout.color_spinner);
        adapter.setDropDownViewResource(R.layout.spinner_drop);
        combito.setAdapter(adapter);

        //Aqui paso el nombre dinamicamente a otra pantalla
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String cliente = bundle.getString("user");
            Cliente.setText(cliente);
        }
        estado.setText("Seleccione un paquete para más mostrar detalles");

        //Aqui vacio la info
        combito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = combito.getSelectedItem().toString();
                if(seleccion.equals("Paquete Oro")){
                    estado.setText("Espuma activa, Lavado, Secado, Abrillantador, Lavado de rines, Detallado, Aspirado, Lavado de chasis y Encerado en pasta.\n");
                }else if (seleccion.equals("Paquete Plata")){
                    estado.setText("Espuma activa, Lavado, Secado, Abrillantador, Lavado de rines, Detallado, Aspirado y Lavado de chasis.\n");
                }
                else if (seleccion.equals("Paquete Bronce")){
                    estado.setText("Espuma activa, Lavado, Secado y Lavado de rines.\n");
                } else{
                    estado.setText("Seleccione un paquete para más mostrar detalles\n");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Bundle bun = new Bundle();
        bun.putString("Paquete", combito.getSelectedItem().toString());
    }

    public void onClick (View view){
        if (combito.getSelectedItem().toString().equals("Paquete Oro") || combito.getSelectedItem().toString().equals("Paquete Plata") || combito.getSelectedItem().toString().equals("Paquete Bronce")){
            Intent miIntent = null;
            switch (view.getId()){
                case R.id.btn_reservahoy:
                    miIntent = new Intent(InicioDeTodo.this, OrdenDeCompra.class);
                    miIntent.putExtra("Datos",combito.getSelectedItem().toString());
                    startActivity(miIntent);
            }
            startActivity(miIntent);
        }else{
            StyleableToast.makeText(InicioDeTodo.this, "Seleccione un paquete", R.style.MiToastRojo).show();
        }
    }
}
