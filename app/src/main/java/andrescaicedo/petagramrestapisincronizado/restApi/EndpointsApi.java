package andrescaicedo.petagramrestapisincronizado.restApi;

import andrescaicedo.petagramrestapisincronizado.restApi.model.FollowersResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.MascotaResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.PerfilResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.SearchResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.TimelineResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface EndpointsApi {


    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<PerfilResponse> getRecentMediaUserID(@Path("id") String id);

    @GET(ConstantesRestApi.URL_SEARCH_USER)
    Call<SearchResponse> getUsuarioBusqueda(@Query("q") String user, @Query("access_token") String access_token);

    //los que sigue el perfil
    @GET(ConstantesRestApi.URL_USER_FOLLOWS)
    Call<FollowersResponse> getUsuarioFollows(@Query("access_token") String access_token);

    //otros que siguen al perfil
    @GET(ConstantesRestApi.URL_USER_FOLLOWS_BY)
    Call<FollowersResponse> getUsuarioFollowers(@Query("access_token") String access_token);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<TimelineResponse> getRecentMediaTimeline(@Path("id") String id);

    //dar like
    //@FormUrlEncoded
    @POST
    Call<MascotaResponse> likeMascotaInstagram(
            @Url String url
            //@Path("media-id") String media_id, @Path("access_token") String access_token
    );
    /**
     * RestApi firebase
     */

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_FIREBASE)
    Call<UsuarioResponse> registrarUsuarioFirebase(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);

    @GET(ConstantesRestApi.KEY_BUSCAR_LLAVE_PARA_LIKE_FIREBASE)
    Call<UsuarioResponse> buscarLlaveParaLikeFirebase(@Path("id_instagram") String id_instagram);

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_REGISTRY_LIKE_FIREBASE)
    Call<UsuarioResponse> registryLikeMascotaFirebase(@Field("id_auto") String id_auto, @Field("id_foto_instagram") String id_foto_instagram,
                                                      @Field("id_usuario_instagram") String id_usuario_instagram, @Field("id_dispositivo") String id_dispositivo);

    @GET(ConstantesRestApi.KEY_LIKE_ANIMAL)
    Call<UsuarioResponse> likeMascotaFirebase(@Path("id") String id, @Path("id_usuario_instagram") String id_usuario_instagram);

}
