package com.equipo.starwash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.equipo.starwash.Entidades.Usuario;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import java.util.regex.Pattern;
import static android.text.TextUtils.isEmpty;

//Clase que usamos para el registro de un usuario
public class IngresoDeUsuario extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //Por lo menos un digito
                    "(?=.*[a-z])" +         //Por lo menos una minuscula
                    "(?=.*[A-Z])" +         //Por lo menos una mayuscula
                    "(?=.*[a-zA-Z])" +      //Cualquier letra
                    ".{5,}" +               //Por lo menos 4 caracteres
                    "$");

    //Declaro los botones y los textos a usar
    private Button btSignIn;
    private Button btSignUp;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_de_usuario);

        btSignIn = findViewById(R.id.bt_acceder);
        btSignUp = findViewById(R.id.bt_registrarNuevo);
        edtName =  findViewById(R.id.txt_nombre);
        edtEmail = findViewById(R.id.txt_correo);
        edtPassword = findViewById(R.id.txt_contrasena);

        final ConexionSQLiteHelper dbHelper = new ConexionSQLiteHelper(this);

        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CamposVacios() && ValidarEmail() && ValidarContra()) {
                    dbHelper.AltaDeUsuario(new Usuario(edtName.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString()));
                    StyleableToast.makeText(IngresoDeUsuario.this, "Usuario registrado con exito", R.style.MiToastVerde).show();
                    edtName.setText("");
                    edtEmail.setText("");
                    edtPassword.setText("");
                }else{
                    StyleableToast.makeText(IngresoDeUsuario.this, "Campos incompletos, intente de nuevo", R.style.MiToastRojo).show();
                }
            }
        });
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!CamposVacios()) {
                    Usuario user = dbHelper.ConsultaUsuario(edtName.getText().toString(), edtEmail.getText().toString(), edtPassword.getText().toString());
                    if (user != null) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("user", user.getNombreCliente());
                        Intent intent = new Intent(IngresoDeUsuario.this, InicioDeTodo.class);

                        //Aqui comparto los objetos entre actividades
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        StyleableToast.makeText(IngresoDeUsuario.this, "Correo utilizado: " + user.getCorreo(), R.style.MiToastVerde).show();
                    } else {
                        StyleableToast.makeText(IngresoDeUsuario.this, "Usuario no registrado", R.style.MiToastRojo).show();
                        edtName.setText("");
                        edtEmail.setText("");
                        edtPassword.setText("");
                    }
                }else{
                    StyleableToast.makeText(IngresoDeUsuario.this, "Campos erróneos, intente de nuevo", R.style.MiToastRojo).show();
                }
            }
        });
    }

    private boolean ValidarEmail(){
        String emailInput = edtEmail.getText().toString().trim();

        if(emailInput.isEmpty()){
            edtEmail.setError("Necesitas de un email para usar la APP.");
            return false;
        } else if(!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            edtEmail.setError("El email debe ser válido, no ficticio.");
            return false;
        } else{
            edtEmail.setError(null);
            return true;
        }
    }

    private boolean ValidarContra(){
        String passwordInput = edtPassword.getText().toString().trim();

        if(passwordInput.isEmpty()){
            edtPassword.setError("Tu contraseña no puede estar en blanco");
            return false;
        } else if(!PASSWORD_PATTERN.matcher(passwordInput).matches()){
            edtPassword.setError("La contraseña debe contener mínimo 5 caracteres.\n\n" +
                    "Por ejemplo: una mayúscula, una minúscula y un número.");
            return false;
        }else{
            edtPassword.setError(null);
            return true;
        }
    }

    private boolean CamposVacios() {
        if (isEmpty(edtEmail.getText().toString()) || isEmpty(edtPassword.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }
}
