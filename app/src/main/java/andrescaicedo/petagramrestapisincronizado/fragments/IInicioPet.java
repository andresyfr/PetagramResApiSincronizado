package andrescaicedo.petagramrestapisincronizado.fragments;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosFollowersAdaptador;
import andrescaicedo.petagramrestapisincronizado.adaptadores.MascotaAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public interface IInicioPet {

    public void generarGridLayout();
    public FotosFollowersAdaptador crearAdaptadorTimeline(ArrayList<Followers> followers);
    public void iniciarAdaptadorRVTimeline(FotosFollowersAdaptador adaptador);
}
