package com.example.andresfg.parcial2bd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andresfg.parcial2bd.Conexion.ConexionBD;
import com.example.andresfg.parcial2bd.Entidades.DetalleVenta;
import com.example.andresfg.parcial2bd.Entidades.Producto;
import com.example.andresfg.parcial2bd.Entidades.Venta;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

public class VentaActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner comboProductos;
    EditText identificacionC;
    ImageButton bton1,bton2,bton3, bton4,bton5;
    TextView listado;
    ConexionBD usuariodb;
    SQLiteDatabase db;
    String cliente;
    String nameUser;
    int idUser;
    int idCliente;
    //
    String ccCliente;
    String idProducto;
    int cantidadProducto;
    String listadoE=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_venta );


        usuariodb = new ConexionBD ( this,"MIBD", null,1 );
        db = usuariodb.getWritableDatabase ();
        listado = (TextView) findViewById ( R.id.tV_listado_productos );
        bton1 = (ImageButton) findViewById(R.id.button_agregar_productos);
        bton1.setOnClickListener(this);
        bton2 = (ImageButton) findViewById(R.id.button_regresar_v);
        bton2.setOnClickListener(this);
        bton3 = (ImageButton) findViewById(R.id.button_confirmar_venta);
        bton3.setOnClickListener(this);
        bton4 = (ImageButton) findViewById(R.id.button_descartar_ventas);
        bton4.setOnClickListener(this);
        bton5 = (ImageButton) findViewById(R.id.button_buscar);
        bton5.setOnClickListener(this);
        identificacionC = (EditText) findViewById ( R.id.eT_identificacionCliente );
        Bundle recibo = getIntent().getExtras();
        if(recibo!=null){
           // ArrayList<DetallePedido> d=null;
            nameUser = recibo.getString ( "nameUser" );
            idUser = recibo.getInt ( "idUser" );
            ccCliente = recibo.getString ("ccCliente");

            String x = listadoE = recibo.getString ( "listado" );
            if(x!=null)
            x = x.replace ( "null","" );
            listadoE = x;
            idProducto = recibo.getString ( "idProducto" );
            cantidadProducto = recibo.getInt ( "cantidadProducto" );
            if(listadoE!=null)
            listado.setText ( listadoE );

            if(ccCliente==null)
                identificacionC.setText ( "");
            else
            identificacionC.setText ( ""+ccCliente );

        }


        identificacionC.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {

// Comprobamos que se ha pulsado la tecla enter.
                if ((event.getAction() == KeyEvent.ACTION_DOWN)&&(keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    idCliente = usuariodb.existeCliente ( identificacionC.getText ().toString () );
                    if(idCliente!=0)
                        Toast.makeText(getApplication (),"existe "+identificacionC.getText ().toString (),Toast.LENGTH_LONG).show();

                    else
                        Toast.makeText(getApplication(),"no existe",Toast.LENGTH_LONG).show();
                    return true;
                }// end if.

                return false;
            }// end onKey.
        });


    }

    public void insertarVentaDetalle(String list){
        Integer total = 0;
        String string= "";
        StringTokenizer tokens=new StringTokenizer(list,"\n");
        while(tokens.hasMoreTokens()){
            //System.out.println(tokens.nextToken());
            string = tokens.nextToken();
            String[] parts = string.split("-");
            int part1 = Integer.parseInt ( parts[0].trim ()); // codigoproducto
            String part2 = parts[1]; // nombreproducto
            int part3 = Integer.parseInt ( parts[2].trim ()); // cantidadproducto
            int part4 = Integer.parseInt ( parts[3].trim ()); // precioTotalProducto
            DetalleVenta dv = new DetalleVenta ( usuariodb.obtenerMax (), part1,part3,part4 );
            usuariodb.insertarDetalleVenta ( dv );
        }

        }

        public String obtenerFecha(){
           /* SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = new Date();

            String fecha = dateFormat.format(date);
            System.out.println(fecha);*/
            GregorianCalendar bday = new GregorianCalendar(1977, Calendar.APRIL, 12);
            int dayOfWeek=bday.get(Calendar.DAY_OF_WEEK);  // Returns 3, for Tuesday!
            Date anotherCurDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("EEEE, d MMMM yyyy ':' hh:mm a ");
            String formattedDateString = formatter.format(anotherCurDate);
            System.out.println(formattedDateString);
            return formattedDateString;
        }

    public int obtenerTotal(String list){
        Integer total = 0;
        String string= "";
        StringTokenizer tokens=new StringTokenizer(list,"\n");
        while(tokens.hasMoreTokens()){
            //System.out.println(tokens.nextToken());
            string = tokens.nextToken();
            String[] parts = string.split("-");
            String part1 = parts[0]; // codigo
            String part2 = parts[1]; // nombreproducto
            String part3 = parts[2]; // cantidad
            String part4 = parts[3].trim (); // total
            int valor = Integer.parseInt ( part4 );
            total += valor;
        }
        return total;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId ()) {

            case R.id.button_buscar:
                Intent ir_venta = new Intent ( VentaActivity.this , ListaVentasActivity.class );
                ir_venta.addFlags ( ir_venta.FLAG_ACTIVITY_CLEAR_TOP | ir_venta.FLAG_ACTIVITY_CLEAR_TASK );
                Bundle datos1 = new Bundle ();
                datos1.putString ( "nameUser" , nameUser );
                datos1.putInt ("idUser", idUser);
                ir_venta.putExtras ( datos1);
                startActivity(ir_venta);

                break;

            case R.id.button_descartar_ventas:
                listado.setText ( "" );
                identificacionC.setText ( "" );
                break;

            case R.id.button_confirmar_venta:
                if(identificacionC.getText ().toString ().trim ().length ()>0 && listadoE!=null){
                    if(usuariodb.existeCliente ( identificacionC.getText ().toString () )!=0){
                        idCliente = usuariodb.idCliente ( identificacionC.getText ().toString () );
                Venta vent = new Venta ( idCliente, obtenerTotal ( listadoE ),obtenerFecha (),idUser );
                usuariodb.insertarVenta ( vent );
                insertarVentaDetalle(listadoE);
                listado.setText ( "" );
                identificacionC.setText ( "" );
                listadoE="";


                    }else{
                        identificacionC.setText ( "" );
                        Toast.makeText(getApplication(),"No existe cliente",Toast.LENGTH_LONG).show();

                    }
                }else{
                    Toast.makeText(getApplication(),"Agregar Productos y/o Cliente",Toast.LENGTH_LONG).show();

                }

                break;
            case R.id.button_agregar_productos:

                if(usuariodb.existeCliente ( identificacionC.getText ().toString () )!=0){
                Intent ir_list = new Intent(VentaActivity.this,ListadoProductosActivity.class);
                ir_list.addFlags(ir_list.FLAG_ACTIVITY_CLEAR_TOP | ir_list.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle datos = new Bundle ();
                ccCliente = identificacionC.getText ().toString ();
               datos.putString ( "nameUser" , nameUser );
                datos.putInt ("idUser", idUser);
               datos.putString ("ccCliente", ccCliente);
               // datos.putParcelable("array", (Parcelable) listaDetallePedido );
                datos.putString ( "listado", listadoE );
                ir_list.putExtras ( datos );

                startActivity(ir_list);
        }else{
                    Toast.makeText(getApplication(),"No existe cliente",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.button_regresar_v:
                Intent ir_home = new Intent(VentaActivity.this,HomeActivity.class);
                ir_home.addFlags(ir_home.FLAG_ACTIVITY_CLEAR_TOP | ir_home.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle datos2 = new Bundle ();
                datos2.putString ( "nameUser" , nameUser );
                datos2.putInt ("idUser", idUser);
                ir_home.putExtras ( datos2 );
                startActivity(ir_home);

              //  Toast.makeText(this,"Good bye "+nameUser,Toast.LENGTH_LONG).show();
                break;

        }

    }

}
