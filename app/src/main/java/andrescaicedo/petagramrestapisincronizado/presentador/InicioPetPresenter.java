package andrescaicedo.petagramrestapisincronizado.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.fragments.IInicioPet;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.restApi.ConstantesRestApi;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;
import andrescaicedo.petagramrestapisincronizado.restApi.adapter.RestApiAdapter;
import andrescaicedo.petagramrestapisincronizado.restApi.model.FollowersResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.PerfilResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.TimelineResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioPetPresenter implements IInicioPetPresenter {

    //Declarando Variables que se ocuparan
    private static final String TAG = "Seguidores";
    IInicioPet iInicioPet;
    Context context;
    ArrayList<Followers> followers;
    private ArrayList<Mascota> mediaRecent;

    public InicioPetPresenter(IInicioPet iInicioPet, Context context) {
        this.iInicioPet = iInicioPet;
        this.context = context;
        getFollowers();
//        getRecentMediaFollowers();
    }

    @Override
    public void mostrarRecentMedioRV() {
        iInicioPet.iniciarAdaptadorRVTimeline(iInicioPet.crearAdaptadorTimeline(followers));
        iInicioPet.generarGridLayout();
    }

    @Override
    public void getFollowers() {
            RestApiAdapter restApiAdapter = new RestApiAdapter();
            Gson gson = restApiAdapter.construyeGsonDeserializadorFollowers();
            EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);
            followers = new ArrayList<>();
            Call<FollowersResponse> perfilResponseCall = endpointsApi.getUsuarioFollowers(ConstantesRestApi.ACCESS_TOKEN);

//            SharedPreferences perfilInstagram = this.context.getSharedPreferences("shared", Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = perfilInstagram.edit();
//            editor.putString("idInstagram", usuario.getId());
//            editor.commit();

            perfilResponseCall.enqueue(new Callback<FollowersResponse>() {
                @Override
                public void onResponse(Call<FollowersResponse> call, Response<FollowersResponse> response) {
                    FollowersResponse followersResponse = response.body();
                    followers = followersResponse.getCuenta();
                    Toast.makeText(context, "Cantidad de seguidos: " + followers.size(), Toast.LENGTH_LONG).show();
                    mostrarRecentMedioRV();
                }

                @Override
                public void onFailure(Call<FollowersResponse> call, Throwable t) {
                    followers = new ArrayList<>();
                    mostrarRecentMedioRV();
                    Toast.makeText(context, "Perfil: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
//        } else {
//            mascotas = new ArrayList<>();
//            mostrarFotosPerfilRV();
//        }


//        RestApiAdapter restApiAdapter = new RestApiAdapter();
//        Gson gson = restApiAdapter.construyeGsonDeserializadorFollowers();
//        EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);
//        followers = new ArrayList<>();
//        Call<FollowersResponse> followersResponseCall = endpointsApi.getUsuarioFollows(ConstantesRestApi.ACCESS_TOKEN);
//        followersResponseCall.enqueue(new Callback<FollowersResponse>() {
//            @Override
//            public void onResponse(Call<FollowersResponse> call, Response<FollowersResponse> response) {
//                FollowersResponse followersResponse = response.body();
//                followers = followersResponse.getCuenta();
//                if (!followers.isEmpty()){
//                    String UsuarioFollowers = followers.get(0).getUsuario().toString(); //DEbug
//                    Log.println(Log.ASSERT, TAG, followers.get(0).getId()); //Debug
//                }
//                getRecentMediaFollowers();
//            }
//            @Override
//            public void onFailure(Call<FollowersResponse> call, Throwable t) {
//                Toast.makeText(context, "Perfil: " + t.toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public void getRecentMediaFollowers() {
//        RestApiAdapter restApiAdapter = new RestApiAdapter();
//        Gson gson = restApiAdapter.construyendoGsonDeserializadoTimeline();
//        EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);
////        mascotas = new ArrayList<>();
//
//        for (int i = 0; i < followers.size() ; i++) {
//            final String idFollower = followers.get(i).getId();
//            Call<TimelineResponse> timelineResponseCall = endpointsApi.getRecentMediaTimeline(idFollower);
//            timelineResponseCall.enqueue(new Callback<TimelineResponse>() {
//                @Override
//                public void onResponse(Call<TimelineResponse> call, Response<TimelineResponse> response) {
//                    TimelineResponse timelineResponse = response.body();
//
//                    ArrayList<Mascota> mascotas = timelineResponse.getMascotas();
//                    if(mascotas!=null){
//                        for (Mascota mascota:mascotas) {
//                            mascota.setId_Cuenta(idFollower);
//                        }
//                        mediaRecent = mascotas;
//                    }
//                    //mostrarRecentMedioRV();
//                }
//
//                @Override
//                public void onFailure(Call<TimelineResponse> call, Throwable t) {
//                    mediaRecent = new ArrayList<>();
//                    Toast.makeText(context, "InicioPetPresent: " + t.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//            followers.get(i).setMediaRecent(mediaRecent);
//        }
    }
}
