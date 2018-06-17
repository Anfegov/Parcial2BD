package com.example.andresfg.parcial2bd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.andresfg.parcial2bd.Conexion.ConexionBD;
import com.example.andresfg.parcial2bd.Fragment.f_ventas_buscar;
import com.example.andresfg.parcial2bd.Fragment.f_ventas_listar;

public class ListaVentasActivity extends AppCompatActivity implements View.OnClickListener, f_ventas_listar.OnFragmentInteractionListener, f_ventas_buscar.OnFragmentInteractionListener {
    ImageButton btonRegresar, btonListar, btonBuscar;
    String nameUser;
    Integer idUser;
    SQLiteDatabase db;
    ConexionBD usuariodb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_lista_ventas );
        usuariodb = new ConexionBD ( this,"MIBD", null,1 );
        db = usuariodb.getWritableDatabase ();
        btonRegresar = (ImageButton) findViewById ( R.id.button_regresar );
        btonListar = (ImageButton) findViewById ( R.id.button_listar_ventas );
        btonBuscar = (ImageButton) findViewById ( R.id.button_buscar_venta );
        btonRegresar.setOnClickListener(this);
        btonListar.setOnClickListener(this);
        btonBuscar.setOnClickListener(this);

        Bundle recibo = getIntent().getExtras();

        if(recibo!=null){
            nameUser = recibo.getString ( "nameUser" );
            idUser = recibo.getInt ( "idUser" );
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.button_listar_ventas:
                f_ventas_listar fvl = new f_ventas_listar ();
                FragmentTransaction transaction1 = getSupportFragmentManager ().beginTransaction ();
                transaction1.replace ( R.id.id_FrameLayout_Principal3 , fvl );
                transaction1.commit ();
                break;
            case R.id.button_regresar:
                Intent ir_regresar = new Intent(ListaVentasActivity.this,VentaActivity.class);
                ir_regresar.addFlags(ir_regresar.FLAG_ACTIVITY_CLEAR_TOP | ir_regresar.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle datos = new Bundle ();
                datos.putString ( "nameUser" , nameUser );
                datos.putInt ("idUser", idUser);
                ir_regresar.putExtras ( datos );
                startActivity(ir_regresar);
                break;
            case R.id.button_buscar_venta:
                f_ventas_buscar fvb = new f_ventas_buscar ();
                FragmentTransaction transaction2 = getSupportFragmentManager ().beginTransaction ();
                transaction2.replace ( R.id.id_FrameLayout_Principal3 , fvb );
                transaction2.commit ();
                break;


        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
