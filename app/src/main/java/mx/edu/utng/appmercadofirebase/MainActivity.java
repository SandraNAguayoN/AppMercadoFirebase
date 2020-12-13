package mx.edu.utng.appmercadofirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Articulo> listArticle = new ArrayList<Articulo>();
    ArrayAdapter<Articulo> arrayAdapterArticulo;


    EditText etNombre;
    EditText etPrecio;
    EditText etCantidad;
    Spinner spiCategoria;
    Spinner spiEnvio;
    Spinner spiGarantia;
    EditText etDescripcion;
    ListView list_articulos;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Articulo articuloSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText)findViewById(R.id.etNombre);
        etPrecio = (EditText)findViewById(R.id.etPrecio);
        etCantidad = (EditText)findViewById(R.id.etCantidad);
        spiCategoria = (Spinner)findViewById(R.id.spiCat);
        spiEnvio = (Spinner)findViewById(R.id.spiEnv);
        spiGarantia = (Spinner)findViewById(R.id.spiGar);
        etDescripcion = (EditText)findViewById(R.id.etDescripcion);

        list_articulos = findViewById(R.id.listaArticulos);

        iniciarFirebase();
        listarDatos();


        list_articulos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                articuloSelected = (Articulo) parent.getItemAtPosition(position);
                /*Intent i = new Intent(MainActivity.this, VerArticulo.class);
                i.putExtra("nombre", articuloSelected.getNombre());
                i.putExtra("precio", articuloSelected.getPrecio());
                i.putExtra("cantidad", articuloSelected.getCantidad());
                i.putExtra("categoria", articuloSelected.getCategoria());
                i.putExtra("envio", articuloSelected.getEnvio());
                i.putExtra("garantia", articuloSelected.getGarantia());
                i.putExtra("descripcion", articuloSelected.getDescripcion());
                startActivity(i);*/
                Intent i = new Intent(MainActivity.this, EditarEliminarArticulo.class);
                i.putExtra("id", articuloSelected.getArticuloId());
                i.putExtra("nombre", articuloSelected.getNombre());
                i.putExtra("precio", articuloSelected.getPrecio());
                i.putExtra("cantidad", articuloSelected.getCantidad());
                i.putExtra("categoria", articuloSelected.getCategoria());
                i.putExtra("envio", articuloSelected.getEnvio());
                i.putExtra("garantia", articuloSelected.getGarantia());
                i.putExtra("descripcion", articuloSelected.getDescripcion());
                startActivity(i);
            }
        });
    }

    private void listarDatos(){
        databaseReference.child("Articulos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listArticle.clear(); //Limpiar lista de personas
                for(DataSnapshot objSnapshot: dataSnapshot.getChildren()) { //Recorrer la variabale a con los objetos
                    Articulo a = objSnapshot.getValue(Articulo.class);
                    listArticle.add(a); //Se agrega el objeto a la lista

                    arrayAdapterArticulo = new ArrayAdapter<Articulo>(MainActivity.this, android.R.layout.simple_list_item_1, listArticle); //Se crea la instancia
                    list_articulos.setAdapter(arrayAdapterArticulo);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void iniciarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //Se crea el menu
        getMenuInflater().inflate(R.menu.menu_main, menu); //Se crea el flater y le pasamos el menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Switch
        switch(item.getItemId()) {
            case R.id.icon_agregar: {
                //Realiza intent hacia la vista de CrearArticulo
                Toast.makeText(this, "Agregar", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, CrearArticulo.class);
                startActivity(i);
                break;
            }
            /*
            case R.id.icon_consultar: {
                Toast.makeText(this, "Consultar", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_editar: {
                Toast.makeText(this, "Editar", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.icon_eliminar: {
                Toast.makeText(this, "Eliminar", Toast.LENGTH_LONG).show();
                break;
            }*/
            default:
                break;
        }
        return true;
    }
}