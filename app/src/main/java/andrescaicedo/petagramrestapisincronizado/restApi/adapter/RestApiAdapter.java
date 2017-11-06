package andrescaicedo.petagramrestapisincronizado.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import andrescaicedo.petagramrestapisincronizado.restApi.ConstantesRestApi;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;
import andrescaicedo.petagramrestapisincronizado.restApi.deserializador.FollowersDeserializador;
import andrescaicedo.petagramrestapisincronizado.restApi.deserializador.PerfilDeserializador;
import andrescaicedo.petagramrestapisincronizado.restApi.deserializador.SearchDeserializador;
import andrescaicedo.petagramrestapisincronizado.restApi.deserializador.TimelineDeserializador;
import andrescaicedo.petagramrestapisincronizado.restApi.model.FollowersResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.PerfilResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.SearchResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.TimelineResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public EndpointsApi establecerConexionInicial(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }

    public Gson construyendoDeserializadorBusqueda(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(SearchResponse.class, new SearchDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializandoFotosPerfil(){
        //Ayuda a Asociar lo que deserialize al contactoREsponse
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(PerfilResponse.class, new PerfilDeserializador());
        //Regresando el gson Deserializado
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFollowers(){
        ////Ayuda a Asociar lo que deserialize al FollowersResponse
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FollowersResponse.class, new FollowersDeserializador());
        //Regresando el gson Deserializado
        return gsonBuilder.create();
    }

    public Gson construyendoGsonDeserializadoTimeline(){
        //Ayuda a Asociar lo que deserialize al TimelineResponse
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(TimelineResponse.class, new TimelineDeserializador());
        //Regresando el gson Deserializado
        return gsonBuilder.create();
    }

    //====================rest api firebase========================================

    public EndpointsApi establecerConexionRestAPIFirebase(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL_FIREBASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(EndpointsApi.class);
    }

}
