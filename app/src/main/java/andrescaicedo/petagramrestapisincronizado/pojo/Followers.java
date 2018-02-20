package andrescaicedo.petagramrestapisincronizado.pojo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ANDRES on 02/10/2017.
 */

public class Followers implements Serializable {

    private String id;
    private String nombre;
    private String usuario;
    private String urlFotoPerfil;

    private ArrayList<Mascota> mediaRecent;

    public Followers(String id, String nombre, String usuario, String urlFotoPerfil) {
        this.id = id;
        this.nombre = nombre;
        this.usuario = usuario;
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public Followers() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    public ArrayList<Mascota> getMediaRecent() {
        return mediaRecent;
    }

    public void setMediaRecent(ArrayList<Mascota> mediaRecent) {
        this.mediaRecent = mediaRecent;
    }
}
