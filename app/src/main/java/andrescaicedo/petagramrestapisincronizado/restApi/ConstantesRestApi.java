package andrescaicedo.petagramrestapisincronizado.restApi;

public final class ConstantesRestApi {

    /**
     * Api, URLS de instagram
     */
    //
    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5707055408.a62d289.101c474e40c3446d9fd1cee136e9b4a0";
//    public static final String ACCESS_TOKEN = "3259702353.aa0d0f4.e61865afc9144ecc8ffc8f6dc84aa17b";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=4027037486.6f9e8f3.d7a35666c7db4519bea8b68754583ddd
    public static final String KEY_GET_RECENT_MEDIA_USER   = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER   = KEY_GET_RECENT_MEDIA_USER+KEY_ACCESS_TOKEN+ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=4027037486.6f9e8f3.d7a35666c7db4519bea8b68754583ddd
    public static final String KEY_GET_RECENT_MEDIA_USER_ID = "users/{id}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER_ID = KEY_GET_RECENT_MEDIA_USER_ID + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    public static final String KEY_SEARCH_USER = "users/search";
    public static final String URL_SEARCH_USER = KEY_SEARCH_USER;

    //https://api.instagram.com/v1/users/self/follows?access_token=4027037486.6f9e8f3.d7a35666c7db4519bea8b68754583ddd
    public static final String KEY_USER_FOLLOWS = "users/self/follows";
    public static final String URL_USER_FOLLOWS = KEY_USER_FOLLOWS;

    //https://api.instagram.com/v1/users/self/followed-by?access_token=4027037486.6f9e8f3.d7a35666c7db4519bea8b68754583ddd
    public static final String KEY_USER_FOLLOWS_BY = "users/self/followed-by";
    public static final String URL_USER_FOLLOWS_BY = KEY_USER_FOLLOWS_BY;

    //dara likes implementado desde el método que lo invoca
    //https://api.instagram.com/v1/media/{media-id}/likes
    //public static final String URL_MEDIA_LIKES   =   "media/{media-id}/likes"+KEY_ACCESS_TOKEN+"{access_token}";

    /**
     * Api, URLS de firebase
     */
    //
    public static final String ROOT_URL_FIREBASE = "https://quiet-shore-79332.herokuapp.com/";
    public static final String KEY_POST_ID_FIREBASE = "usuario_instagram/";

    public static final String KEY_BUSCAR_LLAVE_PARA_LIKE_FIREBASE = "valor/{id_instagram}/";
    public static final String KEY_REGISTRY_LIKE_FIREBASE = "usuario_instagram/registrar_like_foto/";

    /**
     * Api, URLS de firebase curso tres
     */
    ///like-animal/:id/:id_foto_instagram
    public static final String KEY_LIKE_ANIMAL = "like-animal/{id}/{id_usuario_instagram}/";
}
