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

import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.restApi.JsonKeys;
import andrescaicedo.petagramrestapisincronizado.restApi.model.TimelineResponse;

public class TimelineDeserializador implements JsonDeserializer<TimelineResponse> {
    @Override
    public TimelineResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //Deserializando la Respuesta
        Gson gson = new Gson();
        TimelineResponse timelineResponse = gson.fromJson(json, TimelineResponse.class);

        //Declarando el Objeto del Arreglo JSON
        JsonArray timelineResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);
        timelineResponse.setMascotas(deserializarTimelineJson(timelineResponseData));

        return timelineResponse;
    }

    //Arraylist del Recent Media del TimeLine
    private ArrayList<Mascota> deserializarTimelineJson(JsonArray arregloJson){

        ArrayList<Mascota> recentMedia = new ArrayList<>();

        //Para cada elemento del Json
        for (int i = 0; i < arregloJson.size() ; i++) {
            JsonObject recentMediaDataObject = arregloJson.get(i).getAsJsonObject();

            //Obteniendo los Datos de la Imagen Reciente
            String id                   = recentMediaDataObject.get(JsonKeys.ID).getAsString();
            JsonObject imageJson        = recentMediaDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject imagenStdJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto              = imagenStdJson.get(JsonKeys.MEDIA_URL).getAsString();

            //Obteniendo los Likes
            JsonObject likesJson    = recentMediaDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes               = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            //Obteniendo el Usuario
            JsonObject userJson     = recentMediaDataObject.getAsJsonObject(JsonKeys.USER);
            String usuario          = userJson.get(JsonKeys.USER_NAME).getAsString();
            String urlFotoPerfil    = userJson.get(JsonKeys.SEARCH_PROFILEPICTURE).getAsString();

            //Llenando Datos
            Mascota recentMediaActual = new Mascota();
            recentMediaActual.setId(id);
            recentMediaActual.setNombre(usuario);
            recentMediaActual.setUrlFoto(urlFoto);
            recentMediaActual.setLikes(likes);
            recentMediaActual.setUrlFotoPerfil(urlFotoPerfil);

            recentMedia.add(recentMediaActual);

        }
        return recentMedia;
    }

}
