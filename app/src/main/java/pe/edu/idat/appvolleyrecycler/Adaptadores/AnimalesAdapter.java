package pe.edu.idat.appvolleyrecycler.Adaptadores;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pe.edu.idat.appvolleyrecycler.Modelo.Animales;
import pe.edu.idat.appvolleyrecycler.R;

public class AnimalesAdapter extends RecyclerView.Adapter<AnimalesAdapter.AnimalesViewHolder> {

    private Context context;
    private ArrayList<Animales> lista;

    public AnimalesAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public AnimalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(context)
                .inflate(R.layout.item_detalle,
                        parent,false);
        return new AnimalesViewHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull AnimalesViewHolder holder, int position) {
        Animales item = lista.get(position);
        holder.tvNombre.setText("Creador: "+ item.getNombre());
        Picasso.with(context).load(item.getUrlImagen())
                .fit().centerInside()
                .into(holder.ivImagen);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class AnimalesViewHolder extends RecyclerView.ViewHolder{

        ImageView ivImagen;
        TextView tvNombre;

        public AnimalesViewHolder(View itemView) {
            super(itemView);
            ivImagen = itemView.findViewById(R.id.ivImagen);
            tvNombre = itemView.findViewById(R.id.tvNombre);
        }
    }
    public void agregarElemento(ArrayList<Animales> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
