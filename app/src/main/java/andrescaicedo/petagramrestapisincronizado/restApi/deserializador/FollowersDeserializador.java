package andrescaicedo.petagramrestapisincronizado.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.restApi.JsonKeys;
import andrescaicedo.petagramrestapisincronizado.restApi.model.FollowersResponse;

public class FollowersDeserializador implements JsonDeserializer{
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowersResponse followersResponse = gson.fromJson(json, FollowersResponse.class);
        JsonArray arregloJson = json.getAsJsonObject().getAsJsonArray(JsonKeys.FOLLOWERS_ARRAY);
        followersResponse.setCuenta(deserializadorFollowers(arregloJson));
        return followersResponse;
    }

    //Arralist de los Followers
    private ArrayList<Followers> deserializadorFollowers(JsonArray arregloJson){
        ArrayList<Followers> followers = new ArrayList<>();

        //Para cada Elemento del Json
        for (int i = 0; i < arregloJson.size(); i++) {
            JsonObject arregloJsonDataObject = arregloJson.get(i).getAsJsonObject();

            //Obteniendo los Datos del Follower
            String idFollower       = arregloJsonDataObject.get(JsonKeys.FOLLOWERS_USER_ID).getAsString();
            String usernameFollower = arregloJsonDataObject.get(JsonKeys.FOLLOWERS_USER_NAME).getAsString();
            String fullnameFollower = arregloJsonDataObject.get(JsonKeys.FOLLOWERS_USER_FULL_NAME).getAsString();
            String pictureFollower  = arregloJsonDataObject.get(JsonKeys.FOLLOWERS_USER_PROFILE_PIC).getAsString();


            //Llenando Datos
            Followers followerActual = new Followers();
            followerActual.setId(idFollower);
            followerActual.setUsuario(usernameFollower);
            followerActual.setNombre(fullnameFollower);
            followerActual.setUrlFotoPerfil(pictureFollower);

            System.out.println("Followers id: "+idFollower+" nombre: "+usernameFollower+" full name "+fullnameFollower+" picture "+pictureFollower);

            followers.add(followerActual);
        }


        return followers;
    }
}
