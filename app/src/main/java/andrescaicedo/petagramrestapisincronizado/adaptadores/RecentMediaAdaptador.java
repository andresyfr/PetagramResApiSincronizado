package andrescaicedo.petagramrestapisincronizado.adaptadores;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.db.ConstructorMascotas;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.presentador.RecentMediaActivity;
import andrescaicedo.petagramrestapisincronizado.restApi.ConstantesRestApi;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;
import andrescaicedo.petagramrestapisincronizado.restApi.adapter.RestApiAdapter;
import andrescaicedo.petagramrestapisincronizado.restApi.model.MascotaResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDRES on 02/10/2017.
 */

public class RecentMediaAdaptador extends RecyclerView.Adapter<RecentMediaAdaptador.RecentMediaViewHolder>{

    //4.- Declarando la coleccion de los contactos
    ArrayList<Mascota> mascotas;
    Activity activity;

    //1.-
    public static class RecentMediaViewHolder extends RecyclerView.ViewHolder{
        //2.- Se declaran todos los views declarados dentro del cardview aqui es donde se adaptan
        private ImageView imgFotoPerfil;
        private TextView tvLikesCVPerfil;
        private ImageView imgBoneCVPerfil;

        public RecentMediaViewHolder(View itemView) {
            super(itemView);
            // 3.- Se asocia el ImageView y se hace el casting
            imgFotoPerfil         = (ImageView)   itemView.findViewById(R.id.imgFotoPerfil);
            tvLikesCVPerfil       = (TextView)    itemView.findViewById(R.id.tvNombreCV);
            imgBoneCVPerfil       = (ImageView)   itemView.findViewById(R.id.imgBone);
        }

    }

    //10.- Generando el Metodo Constructor
    public RecentMediaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    //6.- Esto infla o le da vida a nuestro layout cardview, Infla el layout y lo pasa al viewholder
    public RecentMediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil, parent, false);
        return new RecentMediaViewHolder(v);
    }

    @Override //5.- EL numero total de mascotas
    public int getItemCount() {
        return mascotas.size();
    }

    @Override //7.- Aqui vamos a pasar la lista de Mascotas
    public synchronized void onBindViewHolder(final RecentMediaViewHolder fotosPerfilViewHolder, final int position) {
        fotosPerfilViewHolder.tvLikesCVPerfil.setText(String.valueOf(mascotas.get(position).getLikes()));
        //fotosPerfilViewHolder.imgFotoPerfil.setImageResource(mascota.getFoto());
        Picasso.with(activity)
                .load(mascotas.get(position).getUrlFoto())
                .placeholder(R.drawable.huella)
                .into(fotosPerfilViewHolder.imgFotoPerfil);
        ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
        final Mascota mascota = mascotas.get(position);
        //8.- Haciendo Click en la Imagen
        fotosPerfilViewHolder.imgFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View v) {
                Toast.makeText(activity, mascota.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        //9.- Haciendo Click en el boton Like
        fotosPerfilViewHolder.imgBoneCVPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public synchronized void onClick(View v) {
                int verificarMascota;
                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                verificarMascota = 0;//constructorMascotas.verificarMascota(mascota);
                if (verificarMascota == 0){
                    //constructorMascotas.insertarMascota(mascota);
                    buscarLLaveParaLikeFirebase(mascota.getId_Cuenta(), mascota.getId());
                    fotosPerfilViewHolder.tvLikesCVPerfil.setText(""+(Integer.parseInt(fotosPerfilViewHolder.tvLikesCVPerfil.getText().toString())+1) );
                    //Toast.makeText(activity, "Like: " + fotosPerfilViewHolder.tvLikesCVPerfil.getText().toString(), Toast.LENGTH_SHORT).show();
                } else {
                    constructorMascotas.insertarLikeMascota(mascota);
                    fotosPerfilViewHolder.tvLikesCVPerfil.setText(Integer.toString(constructorMascotas.obtenerLikesMascota(mascota)));
                    //Toast.makeText(activity, "Like: " + mascota.getId(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static String idAuto=null;
    private static String id_media_receptor=null;

    /**
     * busca la llave en firebase del id de instagram
     * @param id_instagram
     * @param id_media
     */
    public synchronized void buscarLLaveParaLikeFirebase(String id_instagram, String id_media){
        this.id_media_receptor=id_media;

        RestApiAdapter restApiAdapter =  new RestApiAdapter();
        EndpointsApi endponits = restApiAdapter.establecerConexionRestAPIFirebase();

        System.out.println("||buscar llave "+id_instagram+" "+id_media);
        //                                                                 ID_receptor = id pequeño firebase          ID_INSTAGRAM_EMISOR
        Call<UsuarioResponse> usuarioResponseCall = endponits.buscarLlaveParaLikeFirebase(id_instagram) ;
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public synchronized void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse1 =response.body();
                System.out.println("|on response buscar llave ==================="+usuarioResponse1.getId_auto());

                try {
                    idAuto=usuarioResponse1.getId_auto();
                    darLikeFirebase();
                    postLikeMediaInstagram();
                }catch (Exception ex){
                    System.out.println("===Error en Buscar llave=== "+ex);
                }
            }

            @Override
            public synchronized void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println("|on dailure buscar llave========"+call.toString()+"=="+t.toString());
            }
        });
    }

    /**
     * publica un like en la plataforma de instagram
     */
    public synchronized void postLikeMediaInstagram() throws Exception{

        //1.- Inicializando el Adaptador RestApiAdapter
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        //2.- Creando el GSON
        Gson gson = restApiAdapter.construyendoDeserializadorBusqueda();
        //3.- Instanciando la llamada hacia el Endpoint con el gson que acabamos de crear
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);
        //4.- Creando el Call
        String url = "media/"+id_media_receptor+"/likes"+ConstantesRestApi.KEY_ACCESS_TOKEN+ConstantesRestApi.ACCESS_TOKEN;
        //System.out.println("Url fotos perfil adaptador "+url);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.likeMascotaInstagram(url);
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public synchronized void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
//                MascotaResponse mascotaResponse = response.body();
                try{
                    //Toast.makeText(activity, "Se agregó un like a la foto seleccionada,\n configure la cuenta de nuevo para ver los cambios", Toast.LENGTH_LONG).show();
                }catch (Exception ex){
                    System.out.println("ErrorFotosLike "+"\n error "+ex);
                }
            }
            @Override
            public synchronized void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(activity, "Perfil: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * guarda el like en firebase -------------- lo hace bien
     */
    public synchronized void registryLikeFirebase(){

        String idAutoAdondeLlegaNotificacion=idAuto;
        String id_foto_instagram=id_media_receptor;

        SharedPreferences misReferencias = activity.getSharedPreferences("shared", Context.MODE_PRIVATE);

        String idDispositivo = misReferencias.getString("idDispositivo", "");
        String idUsuarioInstagram = misReferencias.getString("idInstagram", "");

        System.out.println("||Registrar Like Firebase"+idAutoAdondeLlegaNotificacion+" "+idUsuarioInstagram+" "+id_foto_instagram+" "+idDispositivo);

        RestApiAdapter restApiAdapter =  new RestApiAdapter();
        EndpointsApi endponits = restApiAdapter.establecerConexionRestAPIFirebase();
        //                                                                 ID_receptor = id pequeño firebase          ID_INSTAGRAM_EMISOR
        Call<UsuarioResponse> usuarioResponseCall = endponits.registryLikeMascotaFirebase(idAutoAdondeLlegaNotificacion,id_foto_instagram,idUsuarioInstagram,idDispositivo);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public synchronized void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
//                UsuarioResponse usuarioResponse1 =response.body();

//                postLikeMediaInstagram(usuarioResponse1.getId_auto(), id_media);
            }

            @Override
            public synchronized void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println("|on failure registrar like====== "+call.toString()+"=="+t.toString());
            }
        });

    }

    /**
     *
     */
    public synchronized void darLikeFirebase()throws Exception{
        SharedPreferences misReferencias = activity.getSharedPreferences("shared", Context.MODE_PRIVATE);
        String id_usuario_instagram = misReferencias.getString("idInstagram", "");

        System.out.println("||dar like "+idAuto+" "+id_usuario_instagram);

        RestApiAdapter restApiAdapter =  new RestApiAdapter();
        EndpointsApi endponits = restApiAdapter.establecerConexionRestAPIFirebase();
        //                                                                 ID_receptor = id pequeño firebase          ID_INSTAGRAM_EMISOR
        Call<UsuarioResponse> usuarioResponseCall = endponits.likeMascotaFirebase(idAuto, id_usuario_instagram) ;
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public synchronized void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
//                UsuarioResponse usuarioResponse1 =response.body();
                registryLikeFirebase();
            }

            @Override
            public synchronized void onFailure(Call<UsuarioResponse> call, Throwable t) {
                System.out.println("|on failure dar like======= "+call.toString()+"=="+t.toString());
            }
        });
    }

}
