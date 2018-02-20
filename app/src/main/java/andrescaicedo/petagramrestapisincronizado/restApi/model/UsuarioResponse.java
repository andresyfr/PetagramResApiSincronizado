package andrescaicedo.petagramrestapisincronizado.restApi.model;

import java.util.ArrayList;

/**
 * Created by ANDRES on 30/10/2017.
 */

public class UsuarioResponse {
    private String id_auto;
    private String id_dispositivo;
    private String id_usuario_instagram;
    private ArrayList id_foto_instagram;

    public UsuarioResponse() {
    }

    public UsuarioResponse(String id_auto, String id_dispositivo, String id_usuario_instagram) {
        this.id_auto = id_auto;
        this.id_dispositivo = id_dispositivo;
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public String getId_auto() {
        return id_auto;
    }

    public void setId_auto(String id_auto) {
        this.id_auto = id_auto;
    }

    public String getId_dispositivo() {
        return id_dispositivo;
    }

    public void setId_dispositivo(String id_dispositivo) {
        this.id_dispositivo = id_dispositivo;
    }

    public String getId_usuario_instagram() {
        return id_usuario_instagram;
    }

    public void setId_usuario_instagram(String id_usuario_instagram) {
        this.id_usuario_instagram = id_usuario_instagram;
    }

    public ArrayList getId_foto_instagram() {
        return id_foto_instagram;
    }

    public void setId_foto_instagram(ArrayList id_foto_instagram) {
        this.id_foto_instagram = id_foto_instagram;
    }
}

