package andrescaicedo.petagramrestapisincronizado.restApi;

import andrescaicedo.petagramrestapisincronizado.restApi.model.FollowersResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.PerfilResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.SearchResponse;
import andrescaicedo.petagramrestapisincronizado.restApi.model.TimelineResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndpointsApi {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<PerfilResponse> getRecentMediaUserID(@Path("id") String id);

    @GET(ConstantesRestApi.URL_SEARCH_USER)
    Call<SearchResponse> getUsuarioBusqueda(@Query("q") String user, @Query("access_token") String access_token);

    @GET(ConstantesRestApi.URL_USER_FOLLOWS)
    Call<FollowersResponse> getUsuarioFollows(@Query("access_token") String access_token);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<TimelineResponse> getRecentMediaTimeline(@Path("id") String id);

}
