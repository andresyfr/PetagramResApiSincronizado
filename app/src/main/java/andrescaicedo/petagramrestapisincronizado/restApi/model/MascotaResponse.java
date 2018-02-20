package andrescaicedo.petagramrestapisincronizado.restApi.model;

/**
 * Created by ANDRES on 02/02/2018.
 */

public class MascotaResponse {

    private String id;
    private String nombre;
    private String nombreCompleto;
    private int likes;
    private String urlFoto;
    private String urlFotoPerfil;

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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getUrlFotoPerfil() {
        return urlFotoPerfil;
    }

    public void setUrlFotoPerfil(String urlFotoPerfil) {
        this.urlFotoPerfil = urlFotoPerfil;
    }

    @Override
    public String toString() {
        return super.toString()+" "+getId()+" "+getNombre()+" "+getNombreCompleto()+" "+getLikes();
    }
}
