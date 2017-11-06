package andrescaicedo.petagramrestapisincronizado.restApi;

import andrescaicedo.petagramrestapisincronizado.restApi.model.FollowersResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.PerfilResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.SearchResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.TimelineResponse;


import andrescaicedo.petagramrestapisincronizado.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<PerfilResponse> getRecentMediaUserID(@Path("id") String id);

    @GET(ConstantesRestApi.URL_SEARCH_USER)
    Call<SearchResponse> getUsuarioBusqueda(@Query("q") String user, @Query("access_token") String access_token);

    @GET(ConstantesRestApi.URL_USER_FOLLOWS)
    Call<FollowersResponse> getUsuarioFollows(@Query("access_token") String access_token);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<TimelineResponse> getRecentMediaTimeline(@Path("id") String id);

    /**
     * RestApi firebase
     */

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_FIREBASE)
    Call<UsuarioResponse> registrarUsuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);


}
