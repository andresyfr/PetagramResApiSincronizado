package andrescaicedo.petagramrestapisincronizado.presentador;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import andrescaicedo.petagramrestapisincronizado.fragments.IPerfilPet;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.restApi.ConstantesRestApi;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;
import andrescaicedo.petagramrestapisincronizado.restApi.adapter.RestApiAdapter;
import andrescaicedo.petagramrestapisincronizado.restApi.model.PerfilResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.SearchResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilPetPresenter implements IPerfilPetPresenter {

    //Declarando variables que se utilizaran en la Clase
    private IPerfilPet iPerfilPet;
    private Context context;
    private String cuentaUsuario;
    ArrayList<Mascota> mascotas;
    private Followers usuario = new Followers();


    //Generamos el Constructor


    public PerfilPetPresenter(IPerfilPet iPerfilPet, Context context, String cuentaUsuario) {
        this.iPerfilPet = iPerfilPet;
        this.context = context;
        this.cuentaUsuario = cuentaUsuario;
        getProfilePicture();
    }


    @Override
    public void getProfile() {

    }

    @Override
    public void mostrarFotosPerfilRV() {
        iPerfilPet.crearImagenPerfil(usuario);
        iPerfilPet.inicializarAdaptadorRV(iPerfilPet.crearAdaptador(mascotas));
        iPerfilPet.generarGridLayout();
    }

    @Override
    public void getInstagramProfile() {
        if(!usuario.getUsuario().equals("NotFound")){
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gson = restApiAdapter.construyeGsonDeserializandoFotosPerfil();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);
            mascotas = new ArrayList<>();
            Call<PerfilResponse> perfilResponseCall = endpointsApi.getRecentMediaUserID(usuario.getId());
            perfilResponseCall.enqueue(new Callback<PerfilResponse>() {
                @Override
                public void onResponse(Call<PerfilResponse> call, Response<PerfilResponse> response) {
                    PerfilResponse perfilResponse = response.body();
                    mascotas = perfilResponse.getMascotas();
                    mostrarFotosPerfilRV();
                }

                @Override
                public void onFailure(Call<PerfilResponse> call, Throwable t) {
                    Toast.makeText(context, "Perfil: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mascotas = new ArrayList<>();
            mostrarFotosPerfilRV();
        }

    }

    @Override
    public void getProfilePicture() {
        //1.- Inicializando el Adaptador RestApiAdapter
        RestApiAdapter restApiAdapter = new RestApiAdapter();

        //2.- Creando el GSON
        Gson gson = restApiAdapter.construyendoDeserializadorBusqueda();

        //3.- Instanciando la llamada hacia el Endpoint con el gson que acabamos de crear
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);

        //4.- Creando el Call
        final Call<SearchResponse> searchResponseCall = endpointsApi.getUsuarioBusqueda(cuentaUsuario, ConstantesRestApi.ACCESS_TOKEN);
        searchResponseCall.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                SearchResponse searchResponse = response.body();
                usuario = searchResponse.getCuenta();
                if(!usuario.getUsuario().equals("NotFound")){
                    getInstagramProfile();
                } else{
                    usuario.setUsuario("NotFound");
                    getInstagramProfile();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {

                Toast.makeText(context, "Perfil: " + t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
