package com.example.andresfg.parcial2bd;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.andresfg.parcial2bd.Fragment.f_cliente_nuevo;
import com.example.andresfg.parcial2bd.Fragment.f_producto_nuevo;
import com.example.andresfg.parcial2bd.Fragment.f_publicidad_nuevo;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, f_publicidad_nuevo.OnFragmentInteractionListener{

    ImageButton btonConfig;
    ImageButton btonVenta;
    ImageButton btonSalir;

    String nameUser;
    int idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_home );
        iniciarFrameLayout();

        btonConfig = (ImageButton) findViewById( R.id.button_configuracion);
        btonVenta = (ImageButton) findViewById( R.id.button_ventas);
        btonSalir = (ImageButton) findViewById( R.id.button_salir);

        btonConfig.setOnClickListener(this);
        btonVenta.setOnClickListener(this);
        btonSalir.setOnClickListener(this);

        Bundle recibo = getIntent().getExtras();

        if(recibo!=null){
            nameUser = recibo.getString ( "nameUser" );
            idUser = recibo.getInt ( "idUser" );
            Toast.makeText(this,"Welcome "+nameUser+":"+idUser,Toast.LENGTH_LONG).show();
        }

    }

    public void iniciarFrameLayout(){
        f_publicidad_nuevo fPublicidad = new f_publicidad_nuevo ();
        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction ();
        transaction1.add( R.id.id_FrameLayout_Principal , fPublicidad );
        transaction1.commit();


    }
    public int getDataFragment(){
        return idUser;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.button_configuracion:
                Intent ir_conf= new Intent ( HomeActivity.this , ConfiguracionesActivity.class );
                ir_conf.addFlags ( ir_conf.FLAG_ACTIVITY_CLEAR_TOP | ir_conf.FLAG_ACTIVITY_CLEAR_TASK );
                Bundle datos = new Bundle ();
                datos.putString ( "nameUser" , nameUser );
                datos.putInt ("idUser", idUser);
                ir_conf.putExtras ( datos );
                startActivity(ir_conf);
                break;
            case R.id.button_ventas:
                Intent ir_venta = new Intent ( HomeActivity.this , VentaActivity.class );
                ir_venta.addFlags ( ir_venta.FLAG_ACTIVITY_CLEAR_TOP | ir_venta.FLAG_ACTIVITY_CLEAR_TASK );
                Bundle datos1 = new Bundle ();
                datos1.putString ( "nameUser" , nameUser );
                datos1.putInt ("idUser", idUser);
                ir_venta.putExtras ( datos1);
                startActivity(ir_venta);
                break;
            case R.id.button_salir:
                Intent ir_salir = new Intent(HomeActivity.this,MainActivity.class);
                ir_salir.addFlags(ir_salir.FLAG_ACTIVITY_CLEAR_TOP | ir_salir.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(ir_salir);
                break;


        }



    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
