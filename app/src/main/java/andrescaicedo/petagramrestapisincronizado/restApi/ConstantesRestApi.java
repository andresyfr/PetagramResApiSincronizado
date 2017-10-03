package andrescaicedo.petagramrestapisincronizado.restApi;

public final class ConstantesRestApi {

    public static final String VERSION = "/v1/";
    public static final String ROOT_URL = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "5707055408.a62d289.101c474e40c3446d9fd1cee136e9b4a0";
    //public static final String ACCESS_TOKEN = "3259702353.aa0d0f4.e61865afc9144ecc8ffc8f6dc84aa17b";
    public static final String KEY_ACCESS_TOKEN = "?access_token=";

    //https://api.instagram.com/v1/users/self/media/recent/?access_token=4027037486.6f9e8f3.d7a35666c7db4519bea8b68754583ddd
    public static final String KEY_GET_RECENT_MEDIA_USER   = "users/self/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER   = KEY_GET_RECENT_MEDIA_USER+KEY_ACCESS_TOKEN+ACCESS_TOKEN;

    //https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=4027037486.6f9e8f3.d7a35666c7db4519bea8b68754583ddd
    public static final String KEY_GET_RECENT_MEDIA_USER_ID = "users/{id}/media/recent/";
    public static final String URL_GET_RECENT_MEDIA_USER_ID = KEY_GET_RECENT_MEDIA_USER_ID + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    public static final String KEY_SEARCH_USER = "users/search";
    public static final String URL_SEARCH_USER = KEY_SEARCH_USER;

    //https://api.instagram.com/v1/users/self/follows?access_token=4027037486.6f9e8f3.d7a35666c7db4519bea8b68754583ddd
    public static final String KEY_USER_FOLLOWS = "users/self/follows";
    public static final String URL_USER_FOLLOWS = KEY_USER_FOLLOWS;




}
