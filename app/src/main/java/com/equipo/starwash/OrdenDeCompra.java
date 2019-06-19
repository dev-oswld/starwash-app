package com.equipo.starwash;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;


public class OrdenDeCompra extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    TextView Paquete, Resultado;
    Button Tiempo;
    RadioButton btnAuto, btnCamioneta;
    int dia, mes, anio, hora, minuto;
    int diaF, mesF, anioF, horaF, minutoF;
    private static String paqueteEscogido, Cadenita, Cita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orden_de_compra);

        //Paso nombre del PAQUETE aqui
        Paquete = findViewById(R.id.txt_paquetefinal);
        Resultado = findViewById(R.id.txt_muestra);
        Bundle pass = getIntent().getExtras();
        paqueteEscogido = pass.getString("Datos");
        Paquete.setText(paqueteEscogido);


        //Selecciono mi hora y fecha
        Tiempo = (Button) findViewById(R.id.btn_tiempo);
        Tiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                anio = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH);
                dia = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(OrdenDeCompra.this, OrdenDeCompra.this, anio, mes, dia);
                datePickerDialog.setMessage("Horario de 8:00 AM- 07:00 PM horas con cobertura de toda la semana");
                datePickerDialog.show();

            }
        });

        //Se establecen los precios
        btnAuto = findViewById(R.id.radioButton1);
        btnCamioneta = findViewById(R.id.radioButton2);
        if (paqueteEscogido.equals("Paquete Oro")) {
            btnAuto.setText("Automóvil $250");
            btnCamioneta.setText("Camioneta $275");
        } else if (paqueteEscogido.equals("Paquete Plata")) {
            btnAuto.setText("Automóvil $110");
            btnCamioneta.setText("Camioneta $125");
        } else if (paqueteEscogido.equals("Paquete Bronce")) {
            btnAuto.setText("Automóvil $75");
            btnCamioneta.setText("Camioneta $85");
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        anioF = i;
        mesF = i1 + 1;
        diaF = i2;

        Calendar c = Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minuto = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(OrdenDeCompra.this, OrdenDeCompra.this, hora, minuto, DateFormat.is24HourFormat(this));
        timePickerDialog.setMessage("Horario de 8:00 AM- 07:00 PM horas con cobertura de toda la semana");
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        horaF = i;
        minutoF = i1;

        if (horaF >= 8 && horaF < 19) {
            Cita = "fecha de " + diaF + "/" + mesF + "/" + anioF + " con cita a " + horaF + ":" + minutoF + " horas";
            Cadenita = "Reservación de " + paqueteEscogido + " con " + Cita; //Mensaje para el QR
            Resultado.setText(Cita);
        } else {
            Cita = "Hora no valida, seleccionar una que sea valida";
            Cadenita = Cita;
            Resultado.setText(Cita);
        }
    }

    public void onClick(View view) {

        if (Cadenita.equals("Hora no valida, seleccionar una que sea valida")) {
            AlertDialog alertDialog = new AlertDialog.Builder(OrdenDeCompra.this).create();
            alertDialog.setTitle("Aviso importante");
            alertDialog.setMessage("Hora no valida, favor de cambiar la hora");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{
            Intent miIntent = null;
            switch (view.getId()){
                case R.id.bt_reserva:
                    miIntent = new Intent(OrdenDeCompra.this, ReciboDeCompra.class);
                    miIntent.putExtra("user",Cadenita); //Importante usar la misma KEY
                    break;
            }
            startActivity(miIntent);
        }
    }
}

  