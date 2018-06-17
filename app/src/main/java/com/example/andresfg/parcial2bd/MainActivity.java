package com.example.andresfg.parcial2bd;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andresfg.parcial2bd.Conexion.ConexionBD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ConexionBD usuariodb;
    EditText usuario;
    EditText pass;
    Button bton;
    String nameUser;
    int idUSer;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        usuariodb = new ConexionBD ( this,"MIBD", null,1 );
        db = usuariodb.getWritableDatabase ();
        usuario = (EditText) findViewById(R.id.editText_usuario);
        pass = (EditText) findViewById(R.id.editText_pass);
        bton = (Button) findViewById(R.id.button_ingresar);
        bton.setOnClickListener(this);

        if(usuariodb.existeUsario ("anfegov@hotmail.com")==false){
            usuariodb.insertarUsuario ("anfegov@hotmail.com", "456");


        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ingresar:
              //  if (usuario.getText ().toString ().trim ().length () > 0 && pass.getText ().toString ().trim ().length () > 0) {
                 //   if (usuariodb.validarIngresoUsario ( usuario.getText ().toString () , pass.getText ().toString () ) == true) {
                        Intent ir_home = new Intent ( MainActivity.this , HomeActivity.class );
                        ir_home.addFlags ( ir_home.FLAG_ACTIVITY_CLEAR_TOP | ir_home.FLAG_ACTIVITY_CLEAR_TASK );
                        nameUser = usuario.getText ().toString ();
                        idUSer = usuariodb.idUsuario ( nameUser );
                        Bundle datos = new Bundle ();
                        datos.putString ( "nameUser" , nameUser );
                        datos.putInt ("idUser", idUSer);
                        ir_home.putExtras ( datos );
                        startActivity ( ir_home );
                      //  } else {
                        usuario.setText ( "" );
                        pass.setText ( "" );
                        Toast.makeText ( this , "Email y/o password incorrecto" , Toast.LENGTH_LONG ).show ();
                  //  }
            //    } else {
                    Toast.makeText ( this , "Campos obligatorios" , Toast.LENGTH_LONG ).show ();

              //  }

                break;
                default:
                    break;
        }

    }
}
