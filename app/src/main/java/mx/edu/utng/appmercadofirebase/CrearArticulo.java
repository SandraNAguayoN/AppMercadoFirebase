package mx.edu.utng.appmercadofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class CrearArticulo extends AppCompatActivity {
    //Clase para manejar la lógica o control de la vista
    EditText etNombre;
    EditText etPrecio;
    EditText etCantidad;
    Spinner spiCategoria;
    Spinner spiEnvio;
    Spinner spiGarantia;
    EditText etDescripcion;
    Button btnRegistrarArticulo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_articulo);

        //Asociar los objetos de control con la vista xml
        etNombre = (EditText) findViewById(R.id.etNombre);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        spiCategoria = (Spinner) findViewById(R.id.spiCat);
        spiEnvio = (Spinner) findViewById(R.id.spiEnv);
        spiGarantia = (Spinner) findViewById(R.id.spiGar);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        btnRegistrarArticulo = (Button) findViewById(R.id.btnRegistrarArticulo);
        iniciarFirebase();

        //Se define el objeto (Clase) haciendo referencia a la conexión de firebase

        //Manejo del evento OnClick
        btnRegistrarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarArticulo(); //Invocar el metodo registrarClase
            }
        });

    } //Fin del OnCreate

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void registrarArticulo() {
        //Obtener los valores que tienen los componentes de la vista
        String nombre = etNombre.getText().toString();
        String precio = etPrecio.getText().toString();
        String cantidad = etCantidad.getText().toString();
        String categoria = spiCategoria.getSelectedItem().toString();
        String envio = spiEnvio.getSelectedItem().toString();
        String garantia = spiGarantia.getSelectedItem().toString();
        String descripcion = etDescripcion.getText().toString();

        if (nombre.equals("") || precio.equals("") || cantidad.equals("") || descripcion.equals("")) { //Si el tema esta correctamente ingresado
            validacion();
            Toast.makeText(this, "Faltan datos por introducir", Toast.LENGTH_LONG).show();
        } else {
            Articulo a = new Articulo();
            a.setArticuloId(UUID.randomUUID().toString());
            a.setNombre(nombre);
            a.setPrecio(precio);
            a.setCantidad(cantidad);
            a.setCategoria(categoria);
            a.setEnvio(envio);
            a.setGarantia(garantia);
            a.setDescripcion(descripcion);
            databaseReference.child("Articulos").child(a.getArticuloId()).setValue(a);
            //Mensaje de error en el registro
            Toast.makeText(this, "Se agregó el artículo", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void limpiar() {
        etNombre.setText("");
        etPrecio.setText("");
        etCantidad.setText("");
        etDescripcion.setText("");
    }

    private void validacion() {
        String nombre = etNombre.getText().toString();
        String precio = etPrecio.getText().toString();
        String cantidad = etCantidad.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        if (nombre.equals("")) {
            etNombre.setError("Required");
        } else {
            if (precio.equals("")) {
                etPrecio.setError("Required");
            } else {
                if (cantidad.equals("")) {
                    etCantidad.setError("Required");
                } else {
                    if (descripcion.equals("")) {
                        etDescripcion.setError("Required");
                    }
                }
            }
        }
    }
}