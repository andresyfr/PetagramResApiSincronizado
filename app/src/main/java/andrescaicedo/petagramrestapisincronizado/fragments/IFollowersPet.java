package andrescaicedo.petagramrestapisincronizado.fragments;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosFollowersAdaptador;
import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosPerfilAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;

public interface IFollowersPet {

    //Generando el Layout Lineal Vertical
    public void generarGridLayout();

    public FotosFollowersAdaptador crearAdaptador(ArrayList<Followers> followers);

    public void inicializarAdaptadorRV(FotosFollowersAdaptador adaptador);

    //public  void crearImagenPerfil(Followers profileUser);

}
