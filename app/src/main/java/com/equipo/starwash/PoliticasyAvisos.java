package com.equipo.starwash;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PoliticasyAvisos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politicasy_avisos);
    }

    public void onClick (View view){
        switch (view.getId()){
            case R.id.btn_llamar:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0123456789"))); //Numero de prueba
                break;

            case R.id.btn_correo:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{"contacto@starwash.com"});
                email.putExtra(Intent.EXTRA_SUBJECT,"Aclaración de dudas | APP");
                email.putExtra(Intent.EXTRA_TEXT,"Tengo una queja con la aplicacion, es la siguiente: ");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email,"Completar acción con"));
                break;
        }
    }
}
