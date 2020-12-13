package mx.edu.utng.appmercadofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.UUID;

public class VerArticulo extends AppCompatActivity {
    //Clase para manejar la lógica o control de la vista
    TextView tvNombre;
    TextView tvPrecio;
    TextView tvCantidad;
    TextView tvCategoria;
    TextView tvEnvio;
    TextView tvGarantia;
    TextView tvDescripcion;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Articulo articuloSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_articulo);

        //Asociar los objetos de control con la vista xml
        tvNombre = (TextView) findViewById(R.id.tvNombre);
        tvPrecio = (TextView) findViewById(R.id.tvPrecio);
        tvCantidad = (TextView) findViewById(R.id.tvCantidad);
        tvCategoria = (TextView) findViewById(R.id.tvCategoria);
        tvEnvio = (TextView) findViewById(R.id.tvEnvio);
        tvGarantia = (TextView) findViewById(R.id.tvGarantia);
        tvDescripcion = (TextView) findViewById(R.id.tvDescripcion);

        iniciarFirebase();
        verDatos();


        //Se define el objeto (Clase) haciendo referencia a la conexión de firebase

    } //Fin del OnCreate

    private void verDatos(){
        Bundle datos = this.getIntent().getExtras();
        String nombrea = datos.getString("nombre");
        tvNombre.setText("Producto:  "+nombrea);
        String precioa = datos.getString("precio");
        tvPrecio.setText("  Precio:  $"+precioa);
        String cantidada = datos.getString("cantidad");
        tvCantidad.setText("  Cantidad:  "+cantidada);
        String categoriaa = datos.getString("categoria");
        tvCategoria.setText("  Categoría:  "+categoriaa);
        String envioa = datos.getString("envio");
        tvEnvio.setText("  Envío:  "+envioa);
        String garantiaa = datos.getString("garantia");
        tvGarantia.setText("  Garantía:  "+garantiaa);
        String descripciona = datos.getString("descripcion");
        tvDescripcion.setText("  Descripción:" + descripciona);

    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


}