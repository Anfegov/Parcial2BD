package com.example.andresfg.parcial2bd.Utilidades;

public class Utilidades {

    //Constantes campos tabla cliente
    public static final String TABLA_CLIENTE = "cliente";
    public static final String CAMPO_ID_C = "idCliente";
    public static final String CAMPO_IDENTIFICACION_C = "identificacion";
    public static final String CAMPO_NOMBRE_C = "nombreCliente";
    public static final String CAMPO_APELLIDO_C = "apellidoCliente";
    public static final String CAMPO_DIR_C = "direccionCliente";





    public static final String CREAR_TABLA_CLIENTE = "CREATE TABLE "+TABLA_CLIENTE+" ("+CAMPO_ID_C+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+CAMPO_IDENTIFICACION_C+" TEXT, "+CAMPO_NOMBRE_C+" TEXT, "+CAMPO_APELLIDO_C+" TEXT, "+CAMPO_DIR_C+" TEXT )";

    //Constantes campos tabla usuario
    public static final String TABLA_USUARIO = "usuario";
    public static final String CAMPO_ID_U = "idUsuario";
    public static final String CAMPO_NOMBRE_U = "nombreUsuario";
    public static final String CAMPO_PASS_U = "password";


    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE "+TABLA_USUARIO+" (" +
            ""+CAMPO_ID_U+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            ""+CAMPO_NOMBRE_U+" TEXT, "+CAMPO_PASS_U+" TEXT)";




    //Constantes campos tabla producto
    public static final String TABLA_PRODUCTO = "producto";
    public static final String CAMPO_ID_P = "idProducto";
    public static final String CAMPO_NOMBRE_P = "nombreProducto";
    public static final String CAMPO_DISPONIBLE_P = "disponibilidad";
    public static final String CAMPO_VALORUNITARIO_P = "valorUnitario";
    public static final String CAMPO_FKUSUARIO_P = "idUsuario";

    public static final String CREAR_TABLA_PRODUCTO = "CREATE TABLE " +
            ""+TABLA_PRODUCTO+" ("+CAMPO_ID_P+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_NOMBRE_P+" TEXT, " +
            ""+CAMPO_DISPONIBLE_P+" INTEGER, "+CAMPO_VALORUNITARIO_P+" INTEGER, " +
            ""+CAMPO_FKUSUARIO_P+" INTEGER, FOREIGN KEY("+CAMPO_FKUSUARIO_P+") REFERENCES " +
            ""+TABLA_USUARIO+"("+CAMPO_ID_U+"))";

    //Constantes campos tabla  venta
    public static final String TABLA_VENTA = "venta";
    public static final String CAMPO_ID_V = "idVenta";
    public static final String CAMPO_IDCLIENTE_V = "idCliente";
    public static final String CAMPO_TOTAL_V = "total";
    public static final String CAMPO_FECHA_V = "fecha";
    public static final String CAMPO_IDUSUARIO_V = "idUsuario";

    public static final String CREAR_TABLA_VENTA = "CREATE TABLE "+TABLA_VENTA+"("+CAMPO_ID_V+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+CAMPO_IDCLIENTE_V+" INTEGER, "+CAMPO_TOTAL_V+" INTEGER, "+CAMPO_FECHA_V+" TEXT, "+CAMPO_IDUSUARIO_V+" INTEGER, " +
            "FOREIGN KEY("+CAMPO_IDCLIENTE_V+") REFERENCES "+TABLA_CLIENTE+"("+CAMPO_ID_C+"), " +
            "FOREIGN KEY("+CAMPO_IDUSUARIO_V+") REFERENCES "+TABLA_USUARIO+"("+CAMPO_ID_U+"))";


    //Constantes campos tabla detalle venta
    public static final String TABLA_DETALLE_V = "detalleVenta";
    public static final String CAMPO_ID_DV = "idDetalleVenta";
    public static final String CAMPO_IDVENTA_DV = "idVenta";
    public static final String CAMPO_IDPRODUCTO_DV = "idProducto";
    public static final String CAMPO_CANTIDAD_DV = "cantidad";
    public static final String CAMPO_PRECIO_DV = "precio ";

    public static final String CREAR_TABLA_VENTA_DETALLE = "CREATE TABLE "+TABLA_DETALLE_V+"("+CAMPO_ID_DV+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ""+CAMPO_IDVENTA_DV+" INTEGER, "+CAMPO_IDPRODUCTO_DV+" INTEGER, "+CAMPO_CANTIDAD_DV+" INTEGER, "+CAMPO_PRECIO_DV+" INTEGER, " +
            "FOREIGN KEY("+CAMPO_ID_DV+" ) REFERENCES "+TABLA_VENTA+" ("+CAMPO_ID_V+"), " +
            "FOREIGN KEY("+CAMPO_IDPRODUCTO_DV+") REFERENCES "+TABLA_PRODUCTO+"("+CAMPO_ID_P+"))";


    //Constantes campos tabla materia prima
    public static final String TABLA_MP = "materiaprima";
    public static final String CAMPO_ID_MP = "id";
    public static final String CAMPO_DESCRIP_MP = "descripcion";
    public static final String CAMPO_DISPONIBLE_MP = "disponibilidad";
    public static final String CAMPO_VALORUNITARIO_MP = "valorUnitario";
    public static final String CAMPO_FKUSUARIO_MP = "idUsuario";

    public static final String CREAR_TABLA_MP= "CREATE TABLE " +
            ""+TABLA_MP+" ("+CAMPO_ID_MP+" INTEGER PRIMARY KEY AUTOINCREMENT, "+CAMPO_DESCRIP_MP+" TEXT, " +
            ""+CAMPO_DISPONIBLE_MP+" INTEGER, "+CAMPO_VALORUNITARIO_MP+" INTEGER, " +
            ""+CAMPO_FKUSUARIO_MP+" INTEGER, FOREIGN KEY("+CAMPO_FKUSUARIO_MP+") REFERENCES " +
            ""+TABLA_USUARIO+"("+CAMPO_ID_U+"))";

}
