package mx.edu.utng.appmercadofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EditarEliminarArticulo extends AppCompatActivity {
    //Clase para manejar la lógica o control de la vista
    EditText etNombre;
    EditText etPrecio;
    EditText etCantidad;
    Spinner spiCategoria;
    Spinner spiEnvio;
    Spinner spiGarantia;
    EditText etDescripcion;
    Button btnEditarArticulo;
    Button btnEliminarArticulo;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String categoriaSeleccionada = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_eliminar_articulo);

        //Asociar los objetos de control con la vista xml
        etNombre = (EditText) findViewById(R.id.etNombre);
        etPrecio = (EditText) findViewById(R.id.etPrecio);
        etCantidad = (EditText) findViewById(R.id.etCantidad);
        spiCategoria = (Spinner) findViewById(R.id.spiCat);
        spiEnvio = (Spinner) findViewById(R.id.spiEnv);
        spiGarantia = (Spinner) findViewById(R.id.spiGar);
        etDescripcion = (EditText) findViewById(R.id.etDescripcion);
        btnEditarArticulo = (Button) findViewById(R.id.btnEditarArticulo);
        btnEliminarArticulo = (Button) findViewById(R.id.btnEliminarArticulo);
        iniciarFirebase();
        mostrar();

        //Se define el objeto (Clase) haciendo referencia a la conexión de firebase

        //Manejo del evento OnClick
        btnEditarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editarArticulo(); //Invocar el metodo registrarClase
            }
        });

        btnEliminarArticulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarArticulo(); //Invocar el metodo registrarClase
            }
        });

    } //Fin del OnCreate

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void mostrar(){
        Bundle datos = this.getIntent().getExtras();
        String ida = datos.getString("id");
        String nombrea = datos.getString("nombre");
        etNombre.setText(nombrea);
        String precioa = datos.getString("precio");
        etPrecio.setText(precioa);
        String cantidada = datos.getString("cantidad");
        etCantidad.setText(cantidada);
        String categoriaa = datos.getString("categoria");
        spiCategoria.getSelectedItemPosition();
        String envioa = datos.getString("envio");
        spiEnvio.getSelectedItemPosition();
        String garantiaa = datos.getString("garantia");
        spiGarantia.getSelectedItemPosition();
        String descripciona = datos.getString("descripcion");
        etDescripcion.setText(descripciona);
    }

    public void editarArticulo() {
        Bundle datos = this.getIntent().getExtras();
        String ida = datos.getString("id");
        Articulo a = new Articulo();
        a.setArticuloId(ida);
        a.setNombre(etNombre.getText().toString().trim());
        a.setPrecio(etPrecio.getText().toString().trim());
        a.setCantidad(etCantidad.getText().toString().trim());
        a.setCategoria(spiCategoria.getSelectedItem().toString().trim());
        a.setEnvio(spiEnvio.getSelectedItem().toString().trim());
        a.setGarantia(spiGarantia.getSelectedItem().toString().trim());
        a.setDescripcion(etDescripcion.getText().toString().trim());
        databaseReference.child("Articulos").child(a.getArticuloId()).setValue(a);
        Toast.makeText(this, "Se actualizó el artículo", Toast.LENGTH_LONG).show();
    }

    public void eliminarArticulo(){
        Bundle datos = this.getIntent().getExtras();
        String ida = datos.getString("id");
        Articulo a = new Articulo();
        a.setArticuloId(ida);
        databaseReference.child("Articulos").child(a.getArticuloId()).removeValue();
        Toast.makeText(this, "Se eliminó el artículo", Toast.LENGTH_LONG).show();
        limpiar();
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