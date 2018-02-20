package andrescaicedo.petagramrestapisincronizado.fragments;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosPerfilAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public interface IPerfilPet {

    //Generando el Layout Lineal Vertical
    public void generarGridLayout();

    public FotosPerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(FotosPerfilAdaptador adaptador);

    public  void crearImagenPerfil(Followers profileUser);

}
