package andrescaicedo.petagramrestapisincronizado.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.restApi.JsonKeys;
import andrescaicedo.petagramrestapisincronizado.restApi.model.SearchResponse;

public class SearchDeserializador implements JsonDeserializer{
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        SearchResponse searchResponse = gson.fromJson(json, SearchResponse.class);
        JsonArray arregloJson = json.getAsJsonObject().getAsJsonArray(JsonKeys.SEARCH_ARRAY);
        searchResponse.setCuenta(deserializadorSearch(arregloJson));
        return searchResponse;
    }

    private Followers deserializadorSearch(JsonArray arregloJson){
        Followers elemento = new Followers();
        if(arregloJson.size() > 0){
            JsonObject searchDataObject = arregloJson.get(0).getAsJsonObject();
            elemento.setNombre(searchDataObject.get(JsonKeys.USER_FULL_NAME).getAsString());
            elemento.setUsuario(searchDataObject.get(JsonKeys.USER_NAME).getAsString());
            elemento.setUrlFotoPerfil(searchDataObject.get(JsonKeys.SEARCH_PROFILEPICTURE).getAsString());
            elemento.setId(searchDataObject.get(JsonKeys.USER_ID).getAsString());
        } else {
            elemento.setUsuario("NotFound");
        }

        return elemento;
    }
}
