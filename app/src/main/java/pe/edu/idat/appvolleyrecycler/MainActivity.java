package pe.edu.idat.appvolleyrecycler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.idat.appvolleyrecycler.Adaptadores.AnimalesAdapter;
import pe.edu.idat.appvolleyrecycler.Modelo.Animales;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvDatos;
    private AnimalesAdapter adapter;
    ArrayList<Animales> vDatos;
    //Declaramos la petición al servicio.
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvDatos = findViewById(R.id.rvDatos);
        rvDatos.setLayoutManager(new LinearLayoutManager(
                MainActivity.this
        ));
        adapter = new AnimalesAdapter(MainActivity.this);
        rvDatos.setAdapter(adapter);
        vDatos = new ArrayList<Animales>();
        //Instanciamos la cola de peticiones.
        mQueue = Volley.newRequestQueue(this);
        //Llamar al método ConsumirWS
        ConsumirWS();
    }

    private void ConsumirWS() {
        //Inicializar el URL del servicio web.
        String url = "https://pixabay.com/api/?key=12544769-ce772d6f6df4078b74b23c3cf&q=yellow+flowers&image_type=photo";
        //Instanciar el objeto request para que sea agregado
        // a la cola de requests.
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray =
                                    response.getJSONArray("hits");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject objImagen
                                        = jsonArray.getJSONObject(i);
                                vDatos.add(new Animales(objImagen.getString("user"),
                                                        objImagen.getString("webformatURL")));

                            }
                            adapter.agregarElemento(vDatos);

                        }catch (JSONException ex){
                            ex.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                }
        );
        mQueue.add(request);
    }
}
