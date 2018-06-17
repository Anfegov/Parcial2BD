package com.example.andresfg.parcial2bd.Fragment;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.andresfg.parcial2bd.Conexion.ConexionBD;
import com.example.andresfg.parcial2bd.Entidades.Cliente;
import com.example.andresfg.parcial2bd.Entidades.Producto;
import com.example.andresfg.parcial2bd.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link f_cliente_nuevo.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link f_cliente_nuevo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class f_cliente_nuevo extends Fragment implements  View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    EditText identificacionC;
    EditText nombreC;
    EditText apellidoC;
    EditText direccionC;
    Button bton;
    ConexionBD usuariodb;
    SQLiteDatabase db;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public f_cliente_nuevo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment f_cliente_nuevo.
     */
    // TODO: Rename and change types and number of parameters
    public static f_cliente_nuevo newInstance(String param1 , String param2) {
        f_cliente_nuevo fragment = new f_cliente_nuevo ();
        Bundle args = new Bundle ();
        args.putString ( ARG_PARAM1 , param1 );
        args.putString ( ARG_PARAM2 , param2 );
        fragment.setArguments ( args );
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        if (getArguments () != null) {
            mParam1 = getArguments ().getString ( ARG_PARAM1 );
            mParam2 = getArguments ().getString ( ARG_PARAM2 );
        }
        usuariodb = new ConexionBD ( getContext (),"MIBD", null,1 );
        db = usuariodb.getWritableDatabase ();
    }


    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup view = (ViewGroup) inflater.inflate ( R.layout.fragment_f_cliente_nuevo , container , false );
        identificacionC = (EditText) view.findViewById ( R.id.eT_identificacionC );
        nombreC = (EditText) view.findViewById ( R.id.eT_nombreC );
        apellidoC = (EditText) view.findViewById ( R.id.eT_apellidoC );
        direccionC = (EditText) view.findViewById ( R.id.eT_direccionC );
        bton = (Button) view.findViewById ( R.id.button_registroC );
        bton.setOnClickListener(this);



        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction ( uri );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_registroC:
                if((identificacionC.getText().toString().trim().length() > 0) && (nombreC.getText().toString().trim().length() > 0) &&
                        (apellidoC.getText().toString().trim().length() > 0) && (direccionC.getText().toString().trim().length() > 0) ){
                    Cliente c = new Cliente ();
                    c.setIdentificacion (  identificacionC.getText ().toString ());
                    c.setNombre ( nombreC.getText ().toString ());
                    c.setApellido ( apellidoC.getText ().toString ());
                    c.setDireccion ( direccionC.getText ().toString ());

                    usuariodb.insertarCliente ( c );
                    // alerta.setText("Exito "+email.getText().toString());
                    Toast.makeText(getActivity(),"Registrado",Toast.LENGTH_LONG).show();

                    identificacionC.setText("");
                    nombreC.setText("");
                    apellidoC.setText("");
                    direccionC.setText("");
                }else{
                    Toast.makeText(getActivity(),"Campos obligatorios",Toast.LENGTH_LONG).show();

                }
                break;
            default:
                break;
        }

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
