package com.example.andresfg.parcial2bd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.andresfg.parcial2bd.Conexion.ConexionBD;
import com.example.andresfg.parcial2bd.Entidades.Producto;

import java.util.ArrayList;

public class ListadoProductosActivity extends AppCompatActivity implements View.OnClickListener {
    private String mParam1;
    private String mParam2;
    Spinner comboProductos;
    ArrayList<String> listaProductos;
    ArrayList<Producto> productosList;
    ConexionBD usuariodb;
    SQLiteDatabase db;
    String cliente;
    Button bton1, bton2;
    EditText cantidad;
        //
    String nameUser;
    int idUser;
    String ccCliente;
    //ArrayList<DetallePedido> listaDetallePedido;
    String idProducto;
    int cantidadProducto;
    String listado="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_listado_productos );
        usuariodb = new ConexionBD ( this,"MIBD", null,1 );
        db = usuariodb.getWritableDatabase ();
        cantidad = (EditText) findViewById ( R.id.eT_valorUnitario ) ;
        bton1 = (Button) findViewById( R.id.button_list_registro);
        bton2 = (Button) findViewById( R.id.button_list_atras);

        bton1.setOnClickListener(this);
        bton2.setOnClickListener(this);

        comboProductos = (Spinner)  findViewById ( R.id.spinner_comboProductos );
        productosList = usuariodb.consultarListaProductos();
        obtenerLista();
        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter (this,android.R.layout.simple_spinner_item,listaProductos);
        comboProductos.setAdapter ( adaptador );
        Bundle recibo = getIntent().getExtras();
        if(recibo!=null){
            nameUser = recibo.getString ( "nameUser" );
            idUser = recibo.getInt ( "idUser" );
            ccCliente = recibo.getString ("ccCliente");
            listado = recibo.getString ( "listado" );
            //listaDetallePedido = recibo.getParcelable ("array");
            Toast.makeText(this,"Welcome "+ccCliente,Toast.LENGTH_LONG).show();

        }


        // cantidad.setText ( ""+idUser );

    }

    private void obtenerLista(){
        listaProductos=new ArrayList<String> (  );
        listaProductos.add("SELECCIONE");

        for(int i=0; i<productosList.size ();i++){
            listaProductos.add(productosList.get ( i ).getIdProducto ()+" - "+productosList.get ( i ).getNombreProducto ());

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {
            case R.id.button_list_registro:
                Intent ir_venta = new Intent ( ListadoProductosActivity.this , VentaActivity.class );
                ir_venta.addFlags ( ir_venta.FLAG_ACTIVITY_CLEAR_TOP | ir_venta.FLAG_ACTIVITY_CLEAR_TASK );
                Bundle datos = new Bundle ();
                datos.putString ( "nameUser" , nameUser );
                datos.putInt ("idUser", idUser);
                datos.putString ("ccCliente", ccCliente);
                cantidadProducto = Integer.parseInt ( cantidad.getText ().toString ());
                idProducto =  String.valueOf(comboProductos.getSelectedItem());
                String string = idProducto;
                String[] parts = string.split("-");
                String nombre = parts[1].trim ();
                int precio = usuariodb.obtenerPrecioProducto ( nombre );
                int total = cantidadProducto*precio;
             //   Toast.makeText(this,"Precio "+nombre,Toast.LENGTH_LONG).show();



                listado+=idProducto+" - "+cantidadProducto+" - "+total+"\n";
                datos.putString ( "listado", listado );
                ir_venta.putExtras ( datos );
                startActivity(ir_venta);
                break;
            case R.id.button_list_atras:
                Intent ir_venta1 = new Intent(ListadoProductosActivity.this,VentaActivity.class);
                ir_venta1.addFlags(ir_venta1.FLAG_ACTIVITY_CLEAR_TOP | ir_venta1.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle datos1 = new Bundle ();
                datos1.putString ( "nameUser" , nameUser );
                datos1.putInt ("idUser", idUser);
                datos1.putString ("ccCliente", ccCliente);
                datos1.putString ( "listado", listado );
               // datos1.putParcelable("array", (Parcelable) listaDetallePedido );
                ir_venta1.putExtras ( datos1 );
                startActivity(ir_venta1);


        }
    }
}
