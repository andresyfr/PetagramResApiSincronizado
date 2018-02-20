package andrescaicedo.petagramrestapisincronizado.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.fragments.IFollowersPet;
import andrescaicedo.petagramrestapisincronizado.fragments.IPerfilPet;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.restApi.ConstantesRestApi;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;
import andrescaicedo.petagramrestapisincronizado.restApi.adapter.RestApiAdapter;
import andrescaicedo.petagramrestapisincronizado.restApi.model.FollowersResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.PerfilResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FollowersPetPresenter implements IFollowersPetPresenter {

    //Declarando variables que se utilizaran en la Clase
    private IFollowersPet ifollowersPet;
    private Context context;
    private String cuentaUsuario;
    ArrayList<Followers> mascotas;
    private Followers usuario = new Followers();


    //Generamos el Constructor


    public FollowersPetPresenter(IFollowersPet iPerfilPet, Context context, String cuentaUsuario) {
        this.ifollowersPet = iPerfilPet;
        this.context = context;
        this.cuentaUsuario = cuentaUsuario;
        //getProfilePicture();
    }

    @Override
    public void mostrarFotosPerfilRV() {
        //ifollowersPet.crearImagenPerfil(usuario);
        ifollowersPet.inicializarAdaptadorRV(ifollowersPet.crearAdaptador(mascotas));
        ifollowersPet.generarGridLayout();
    }

    @Override
    public void getInstagramProfile() {
        if(!usuario.getUsuario().equals("NotFound")){
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gson = restApiAdapter.construyeGsonDeserializadorFollowers();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);
            mascotas = new ArrayList<>();
            Call<FollowersResponse> perfilResponseCall = endpointsApi.getUsuarioFollows(ConstantesRestApi.ACCESS_TOKEN);

            SharedPreferences perfilInstagram = this.context.getSharedPreferences("shared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = perfilInstagram.edit();
            editor.putString("idInstagram", usuario.getId());
            editor.commit();

            Toast.makeText(context, "Id Usuario: " + usuario.getId(), Toast.LENGTH_LONG).show();

            perfilResponseCall.enqueue(new Callback<FollowersResponse>() {
                @Override
                public void onResponse(Call<FollowersResponse> call, Response<FollowersResponse> response) {
                    FollowersResponse perfilResponse = response.body();
                    mascotas = perfilResponse.getCuenta();
                    mostrarFotosPerfilRV();
                }

                @Override
                public void onFailure(Call<FollowersResponse> call, Throwable t) {
                    mascotas = new ArrayList<>();
                    mostrarFotosPerfilRV();
                    Toast.makeText(context, "Perfil: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mascotas = new ArrayList<>();
            mostrarFotosPerfilRV();
        }

    }

}
