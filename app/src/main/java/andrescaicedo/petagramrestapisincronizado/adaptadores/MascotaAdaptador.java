package andrescaicedo.petagramrestapisincronizado.adaptadores;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.db.ConstructorMascotas;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

/**
 * Created by ANDRES on 02/10/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder>{

    //4.- Declarando la coleccion de los contactos
    ArrayList<Mascota> mascotas;
    Activity activity;

    //10.- Generando el metodo Constructor


    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    //6.- Esto infla o le da vida a nuestro layout cardview, Infla el layout y lo pasa al viewholder
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_contacto, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override //7.- Aqui vamos a pasar la lista de Mascotas
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {

        ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);

        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.tvNombreCV.setText(String.valueOf(mascota.getNombre()));
        mascotaViewHolder.tvLikesCV.setText(Integer.toString(mascota.getLikes()));
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.huella)
                .into(mascotaViewHolder.imgFoto);

        Picasso.with(activity)
                .load(mascota.getUrlFotoPerfil())
                .placeholder(R.drawable.huella)
                .into(mascotaViewHolder.imgFotoPerfil);

        //8.- Haciendo Click en la Imagen
        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, mascota.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

        //9.- Haciendo Click en el boton Like
        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int verificarMascota;
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);

                verificarMascota = constructorMascotas.verificarMascota(mascota);

                if (verificarMascota == 0){
                    constructorMascotas.insertarMascota(mascota);
                    Toast.makeText(activity, "Like: " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                } else {
                    constructorMascotas.insertarLikeMascota(mascota);
                    mascotaViewHolder.tvLikesCV.setText(Integer.toString(constructorMascotas.obtenerLikesMascota(mascota)));
                    Toast.makeText(activity, "Like: " + mascota.getNombre(), Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override //5.- EL numero total de contactos
    public int getItemCount() {
        return mascotas.size();
    }

    //1.-Creando Clase Inner Statica de View Holer
    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        //2.- Se declaran todos los views declarados dentro del cardview aqui es donde se adaptan
        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvLikesCV;
        private ImageButton btnLike;
        private ImageView imgFotoPerfil;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            // 3.- Se asocia el ImageView y se hace el casting
            imgFoto         = (ImageView)   itemView.findViewById(R.id.imgFoto);
            tvNombreCV      = (TextView)    itemView.findViewById(R.id.tvNombreCV);
            tvLikesCV       = (TextView)    itemView.findViewById(R.id.tvLikes);
            btnLike         = (ImageButton) itemView.findViewById(R.id.btnLike);
            //imgFotoPerfil   = (ImageView)   itemView.findViewById(R.id.civFotoPerfil);
        }
    }
}