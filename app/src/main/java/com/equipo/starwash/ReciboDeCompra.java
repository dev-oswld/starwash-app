package com.equipo.starwash;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView; //Libs para el QR

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class ReciboDeCompra extends AppCompatActivity {

    ImageView iv_qrcode;
    private static String datosfinales = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recibo_de_compra);

        iv_qrcode=findViewById(R.id.iv_qrcode);
        Bundle bundle = getIntent().getExtras();
        datosfinales = bundle.getString("user");

        //Llenado de datos de la clase OrdenDeCompra
        String contenido = "" + datosfinales;
        contenido = bundle.getString("user");

        WindowManager windowManager = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display display=windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int x =point.x;
        int y =point.y;

        int icon = x<y? x:y;
        icon = icon*6/10;

        QRCodeEncoder qrCodeEncoder=new QRCodeEncoder(contenido,null,Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(), icon);

        try {
            Bitmap bitmap=qrCodeEncoder.encodeAsBitmap();
            iv_qrcode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}


