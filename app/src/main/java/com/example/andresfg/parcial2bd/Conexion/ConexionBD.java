package com.example.andresfg.parcial2bd.Conexion;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.andresfg.parcial2bd.Codificacion.MdCinco;
import com.example.andresfg.parcial2bd.Entidades.Cliente;
import com.example.andresfg.parcial2bd.Entidades.DetalleVenta;
import com.example.andresfg.parcial2bd.Entidades.MateriaPrima;
import com.example.andresfg.parcial2bd.Entidades.Producto;
import com.example.andresfg.parcial2bd.Entidades.Venta;
import com.example.andresfg.parcial2bd.Utilidades.Utilidades;

import java.util.ArrayList;

public class ConexionBD extends SQLiteOpenHelper {
    SQLiteDatabase db;

    String sqlVenta = "CREATE TABLE venta(idVenta INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idCliente INTEGER, Total INTEGER, Fecha Datetime, idUsuario INTEGER, " +
            "FOREIGN KEY(idCliente) REFERENCES cliente(idCliente), " +
            "FOREIGN KEY(idUsuario) REFERENCES usuario(idUsuario))";

    String sqlDetalleVenta = "CREATE TABLE detalleVenta(idDetalleVenta INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "idVenta INTEGER, idProducto INTEGER, cantidad INTEGER, precio INTEGER, " +
            "FOREIGN KEY(idVenta ) REFERENCES venta(idVenta ), " +
            "FOREIGN KEY(idProducto) REFERENCES producto(idProducto))";


    public ConexionBD(Context context , String name , SQLiteDatabase.CursorFactory factory , int version) {
        super ( context , name , factory , version );
    }



    public void insertarUsuario(String nombreUsuario,String password){
        db = getWritableDatabase ();
        ContentValues nuevo_registro = new ContentValues (  );
        MdCinco m = new MdCinco ();
        String passC = m.MD5 ( password );
        nuevo_registro.put(Utilidades.CAMPO_NOMBRE_U, nombreUsuario.toUpperCase ());
        nuevo_registro.put ( Utilidades.CAMPO_PASS_U, passC);
        db.insert ( Utilidades.TABLA_USUARIO,null,nuevo_registro );
        db.close ();
    }

    public boolean validarIngresoUsario(String usuario,String pass){
        db = getWritableDatabase ();
        boolean estado = false;
        try {
            Cursor micursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE_U+", "+Utilidades.CAMPO_PASS_U+" FROM "+Utilidades.TABLA_USUARIO,null);
            if(micursor.moveToFirst ()){
                do{
                    String name = micursor.getString ( 0 );
                    String pas = micursor.getString ( 1 );
                    MdCinco m = new MdCinco ( );
                    if(usuario.toUpperCase ().equals(name)&& m.MD5 ( pass ).equals(pas)) {
                        estado = true;
                    }
                }while(micursor.moveToNext ());
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        db.close ();
        return estado;
    }

    public boolean existeUsario(String nombreUsuario){
        db = getWritableDatabase ();
        boolean estado = false;
        try {
            Cursor micursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE_U+" FROM "+Utilidades.TABLA_USUARIO,null);
            if(micursor.moveToFirst ()){
                do{
                    String name = micursor.getString ( 0 );
                    if(nombreUsuario.toUpperCase ().equals(name)) {
                        estado = true;
                    }
                }while(micursor.moveToNext ());
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        db.close ();
        return estado;
    }

    public int idUsuario(String nombreUsuario){
        int idd=0;
        db = getWritableDatabase ();
        boolean estado = false;
        try {
            Cursor micursor = db.rawQuery("SELECT idUsuario, nombreUsuario FROM Usuario ",null);

            if(micursor.moveToFirst ()){
                do{
                    Integer id = micursor.getInt ( 0 );
                    String name = micursor.getString ( 1 );
                    if(nombreUsuario.toUpperCase ().equals(name)) {
                        idd = id;
                    }
                }while(micursor.moveToNext ());
            }
        } catch (Exception e) {
            //Toast.makeText(this,"Error BD",Toast.LENGTH_LONG).show();
            e.printStackTrace ();
        }
        db.close ();

        return idd;
    }

    public int idCliente(String identificacion){
        int idd=0;
        db = getWritableDatabase ();
        boolean estado = false;
        try {
            Cursor micursor = db.rawQuery("SELECT idCliente, identificacion FROM cliente ",null);

            if(micursor.moveToFirst ()){
                do{
                    Integer id = micursor.getInt ( 0 );
                    String identifiacion = micursor.getString ( 1 );
                    if(identifiacion.toUpperCase ().equals(identificacion)) {
                        idd = id;
                    }
                }while(micursor.moveToNext ());
            }
        } catch (Exception e) {
            //Toast.makeText(this,"Error BD",Toast.LENGTH_LONG).show();
            e.printStackTrace ();
        }
        db.close ();

        return idd;
    }

    public void insertarProducto(Producto p){
        db = getWritableDatabase ();
        ContentValues nuevo_registro = new ContentValues (  );
        nuevo_registro.put(Utilidades.CAMPO_NOMBRE_P, p.getNombreProducto ().toUpperCase ());
        nuevo_registro.put(Utilidades.CAMPO_DISPONIBLE_P, p.getDisponiblidad ());
        nuevo_registro.put(Utilidades.CAMPO_VALORUNITARIO_P, p.getValorUnitario ());
        nuevo_registro.put(Utilidades.CAMPO_FKUSUARIO_P, p.getIdUsuario ());

        db.insert ( Utilidades.TABLA_PRODUCTO,null,nuevo_registro );
        db.close ();

    }

    public void insertarMP(MateriaPrima m){
        db = getWritableDatabase ();
        ContentValues nuevo_registro = new ContentValues (  );
        nuevo_registro.put(Utilidades.CAMPO_DESCRIP_MP, m.getDescripcion ().toUpperCase ());
        nuevo_registro.put(Utilidades.CAMPO_DISPONIBLE_MP, m.getDisponibilidad ());
        nuevo_registro.put(Utilidades.CAMPO_VALORUNITARIO_MP, m.getValorUnitario ());
        nuevo_registro.put(Utilidades.CAMPO_FKUSUARIO_MP, m.getIdUsuario ());

        db.insert ( Utilidades.TABLA_PRODUCTO,null,nuevo_registro );
        db.close ();

    }

    public ArrayList<Producto> consultarListaProductos(){
        db = getReadableDatabase ();
        Producto producto=null;
        ArrayList<Producto> productosList = new ArrayList<Producto> ();
        //
        Cursor cursor = db.rawQuery ( "SELECT * FROM producto",null );

        while(cursor.moveToNext ()){
            producto = new Producto (  );
            producto.setIdProducto ( cursor.getInt ( 0 ) );
            producto.setNombreProducto ( cursor.getString ( 1 ) );
            producto.setDisponiblidad ( cursor.getInt ( 2 ) );
            producto.setValorUnitario ( cursor.getInt ( 3 ) );
            producto.setIdUsuario ( cursor.getInt ( 4 ) );
            productosList.add(producto);

        }

        return productosList;
    }


    public void insertarCliente(Cliente c){

        db = getWritableDatabase ();
        ContentValues nuevo_registro = new ContentValues (  );
        nuevo_registro.put(Utilidades.CAMPO_ID_C,c.getId ());
        nuevo_registro.put(Utilidades.CAMPO_IDENTIFICACION_C, c.getIdentificacion ());
        nuevo_registro.put ( Utilidades.CAMPO_NOMBRE_C, c.getNombre ().toUpperCase ());
        nuevo_registro.put ( Utilidades.CAMPO_APELLIDO_C, c.getApellido ().toUpperCase ());
        nuevo_registro.put ( Utilidades.CAMPO_DIR_C, c.getDireccion ().toUpperCase ());


        db.insert ( Utilidades.TABLA_CLIENTE,null,nuevo_registro );
        db.close ();

    }

    public void insertarVenta(Venta v){
        db = getWritableDatabase ();
        ContentValues nuevo_registro = new ContentValues (  );
        nuevo_registro.put(Utilidades.CAMPO_IDCLIENTE_V, v.getIdCliente ());
        nuevo_registro.put ( Utilidades.CAMPO_TOTAL_V , v.getTotal ());
        nuevo_registro.put ( Utilidades.CAMPO_FECHA_V, v.getDatetime ());
        nuevo_registro.put ( Utilidades.CAMPO_IDUSUARIO_V, v.getIdUsuario ());


        db.insert ( Utilidades.TABLA_VENTA,null,nuevo_registro );
        db.close ();

    }

    public void insertarDetalleVenta(DetalleVenta v){
        db = getWritableDatabase ();
        ContentValues nuevo_registro = new ContentValues (  );
        nuevo_registro.put(Utilidades.CAMPO_IDVENTA_DV, v.getId_venta ());
        nuevo_registro.put ( Utilidades.CAMPO_IDPRODUCTO_DV , v.getId_producto ());
        nuevo_registro.put ( Utilidades.CAMPO_CANTIDAD_DV, v.getCantidad ());
        nuevo_registro.put ( Utilidades.CAMPO_PRECIO_DV, v.getPrecio ());


        db.insert ( Utilidades.TABLA_DETALLE_V,null,nuevo_registro );
        db.close ();

    }

    public int existeCliente(String identificacion){
       int id_c=0;
        db = getWritableDatabase ();
        boolean estado = false;
        try {
            Cursor micursor = db.rawQuery("SELECT "+Utilidades.CAMPO_ID_C+", "+Utilidades.CAMPO_IDENTIFICACION_C+", "+Utilidades.CAMPO_NOMBRE_C+"  FROM "+Utilidades.TABLA_CLIENTE,null);
            if(micursor.moveToFirst ()){
                do{
                    int id = micursor.getInt (0  );
                    String identifi = micursor.getString ( 1 );
                    String name = micursor.getString ( 2 );
                    if(identificacion.equals(identifi)) {
                        id_c=id;
                    }
                }while(micursor.moveToNext ());
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        db.close ();
        return id_c;
    }

    public int obtenerPrecioProducto(String nombreProducto){
        int precio=0;
        int idproducto;
        db = getWritableDatabase ();
        boolean estado = false;
        try {
            Cursor micursor = db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE_P+", "+Utilidades.CAMPO_VALORUNITARIO_P+ " FROM "+Utilidades.TABLA_PRODUCTO,null);
            if(micursor.moveToFirst ()){
                do{
                    String nombre = micursor.getString ( 0 );
                    int preciox = micursor.getInt ( 1 );
                    if(nombre.trim ().equals ( nombreProducto.trim () )){
                        precio=preciox;
                    }

                }while(micursor.moveToNext ());
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        db.close ();
        return precio;
    }

    public int obtenerMax(){
        int id_venta=0;
      db = getWritableDatabase ();
        boolean estado = false;
        try {
            Cursor micursor = db.rawQuery("SELECT MAX("+Utilidades.CAMPO_ID_V+") FROM "+Utilidades.TABLA_VENTA,null);
            if(micursor.moveToFirst ()){
                do{

                    id_venta = micursor.getInt ( 0 );


                }while(micursor.moveToNext ());
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        db.close ();
        return id_venta;
    }

    public String obtenerlistaVenta(){
        String list="";
        Integer suma=0;
        db = getWritableDatabase ();
        try{
        Cursor micursor = db.rawQuery("select cliente.nombreCliente, venta.total, venta.fecha from venta inner join cliente on venta.idCliente = cliente.idCliente",null);
        //"SELECT T2."+Utilidades.CAMPO_NOMBRE_C+", T1."+Utilidades.CAMPO_TOTAL_V+", T1."+Utilidades.CAMPO_FECHA_V+" FROM "+Utilidades.TABLA_VENTA+" AS T1 INNER JOIN "+Utilidades.TABLA_CLIENTE+" AS T2 ON T1."+Utilidades.CAMPO_ID_V+" = T2."+Utilidades.CAMPO_ID_V
        String text="";
        if(micursor.moveToFirst ()){
                do{
                    String nombre = micursor.getString ( 0 );
                    int total = micursor.getInt (  1);
                    String fecha = micursor.getString ( 2 );
                    text+=nombre+" "+total+" "+fecha+"\n";
                    suma+=total;


                }while(micursor.moveToNext ());
                text+="\nTOTAL "+suma;
                return text;
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        db.close ();
        return list;
    }

    public String obtenerVenta(String cc){
        String list="";
        Integer suma=0;
        db = getWritableDatabase ();
        try{
            Cursor micursor = db.rawQuery("select cliente.nombreCliente, venta.total, venta.fecha from venta inner join cliente on venta.idCliente = cliente.idCliente where cliente.identificacion="+cc,null);
            //"SELECT T2."+Utilidades.CAMPO_NOMBRE_C+", T1."+Utilidades.CAMPO_TOTAL_V+", T1."+Utilidades.CAMPO_FECHA_V+" FROM "+Utilidades.TABLA_VENTA+" AS T1 INNER JOIN "+Utilidades.TABLA_CLIENTE+" AS T2 ON T1."+Utilidades.CAMPO_ID_V+" = T2."+Utilidades.CAMPO_ID_V
            String text="";
            if(micursor.moveToFirst ()){
                do{
                    String nombre = micursor.getString ( 0 );
                    int total = micursor.getInt (  1);
                    String fecha = micursor.getString ( 2 );
                    text+=nombre+" "+total+" "+fecha+"\n";
                    suma+=total;


                }while(micursor.moveToNext ());
                text+="\nTOTAL "+suma;
                return text;
            }
        } catch (Exception e) {
            e.printStackTrace ();
        }
        db.close ();
        return list;
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
//        db = getWritableDatabase ();
        db.execSQL ( Utilidades.CREAR_TABLA_USUARIO );
        db.execSQL ( Utilidades.CREAR_TABLA_CLIENTE );
        db.execSQL ( Utilidades.CREAR_TABLA_PRODUCTO );
        db.execSQL ( Utilidades.CREAR_TABLA_VENTA );
        //db.execSQL ( sqlVenta );
        db.execSQL (  Utilidades.CREAR_TABLA_VENTA_DETALLE );
        db.execSQL (  Utilidades.CREAR_TABLA_MP );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion) {
        db.execSQL ( "DROP TABLE IF EXISTS "+ Utilidades.TABLA_USUARIO );
        db.execSQL ( "DROP TABLE IF EXISTS "+ Utilidades.TABLA_CLIENTE );
        db.execSQL ( "DROP TABLE IF EXISTS "+ Utilidades.TABLA_PRODUCTO );
        db.execSQL ( "DROP TABLE IF EXISTS venta" );
        db.execSQL ( "DROP TABLE IF EXISTS "+ Utilidades.TABLA_DETALLE_V );
        db.execSQL ( "DROP TABLE IF EXISTS "+ Utilidades.TABLA_MP);
        db.execSQL ( Utilidades.TABLA_USUARIO );
        db.execSQL ( Utilidades.TABLA_CLIENTE );
        db.execSQL ( Utilidades.TABLA_PRODUCTO );
        db.execSQL ( sqlVenta );
        db.execSQL ( Utilidades.TABLA_DETALLE_V  );
        db.execSQL ( Utilidades.TABLA_MP  );


    }

}
