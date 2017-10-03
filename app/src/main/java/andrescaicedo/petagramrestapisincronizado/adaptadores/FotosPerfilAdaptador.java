package andrescaicedo.petagramrestapisincronizado.adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

/**
 * Created by ANDRES on 02/10/2017.
 */

public class FotosPerfilAdaptador extends RecyclerView.Adapter<FotosPerfilAdaptador.FotosPerfilViewHolder>{

    //4.- Declarando la coleccion de los contactos
    ArrayList<Mascota> mascotas;
    Activity activity;

    //1.-
    public static class FotosPerfilViewHolder extends RecyclerView.ViewHolder{

        //2.- Se declaran todos los views declarados dentro del cardview aqui es donde se adaptan
        private ImageView imgFotoPerfil;
        private TextView tvLikesCVPerfil;

        public FotosPerfilViewHolder(View itemView) {
            super(itemView);
            // 3.- Se asocia el ImageView y se hace el casting
            imgFotoPerfil         = (ImageView)   itemView.findViewById(R.id.imgFotoPerfil);
            tvLikesCVPerfil       = (TextView)    itemView.findViewById(R.id.tvNombreCV);
        }
    }

    //10.- Generando el Metodo Constructor
    public FotosPerfilAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    //6.- Esto infla o le da vida a nuestro layout cardview, Infla el layout y lo pasa al viewholder
    public FotosPerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new FotosPerfilViewHolder(v);
    }

    @Override //7.- Aqui vamos a pasar la lista de Mascotas
    public void onBindViewHolder(FotosPerfilViewHolder fotosPerfilViewHolder, final int position) {
        fotosPerfilViewHolder.tvLikesCVPerfil.setText(String.valueOf(mascotas.get(position).getLikes()));
        //fotosPerfilViewHolder.imgFotoPerfil.setImageResource(mascota.getFoto());
        Picasso.with(activity)
                .load(mascotas.get(position).getUrlFoto())
                .placeholder(R.drawable.huella)
                .into(fotosPerfilViewHolder.imgFotoPerfil);
    }

    @Override //5.- EL numero total de mascotas
    public int getItemCount() {
        return mascotas.size();
    }

}
