package com.equipo.starwash;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    public static final String PREFERENCIA = "MyPrefsFile";
    public CheckBox CajadeNoMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        LayoutInflater adbInflater = LayoutInflater.from(this);
        View eulaLayout = adbInflater.inflate(R.layout.checkbox, null);
        SharedPreferences settings = getSharedPreferences(PREFERENCIA, 0);
        String skipMessage = settings.getString("skipMessage", "NOT checked");

        CajadeNoMostrar = (CheckBox) eulaLayout.findViewById(R.id.skip);
        adb.setView(eulaLayout);
        //Mensaje de terminos y condiciones
        adb.setTitle(Html.fromHtml("<b>"+"POLÍTICA DE PRIVACIDAD"+"</b>"));
        StringBuilder Construyo = new StringBuilder();
        Construyo.append("El aviso de privacidad y la política del mismo, tiene que ser leída antes de aceptar los términos y condiciones. Este mensaje seguirá hasta que el usuario haya completado la acción.");
        adb.setMessage(Construyo.toString());

        adb.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";

                if (CajadeNoMostrar.isChecked()) {
                    checkBoxResult = "checked";
                }

                SharedPreferences settings = getSharedPreferences(PREFERENCIA, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessage", checkBoxResult);
                editor.commit();
                return;
            }
        });

        adb.setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                String checkBoxResult = "NOT checked";

                if (CajadeNoMostrar.isChecked()) {
                    checkBoxResult = "checked";
                }

                SharedPreferences settings = getSharedPreferences(PREFERENCIA, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("skipMessage", checkBoxResult);
                editor.commit();
                return;
            }
        });

        if (!skipMessage.equals("checked")) { //Aqui salto o no segun el mensaje del checkbox
            adb.show();
        }
        super.onResume();
    }

    public void onClick (View view){
        Intent miIntent = null;
        switch (view.getId()){
            case R.id.bt_ingresa:
                miIntent = new Intent(MainActivity.this, IngresoDeUsuario.class);
                break;

            case R.id.txt_politicas:
                miIntent = new Intent(MainActivity.this, PoliticasyAvisos.class);
                break;
        }
        startActivity(miIntent);
    }
}
