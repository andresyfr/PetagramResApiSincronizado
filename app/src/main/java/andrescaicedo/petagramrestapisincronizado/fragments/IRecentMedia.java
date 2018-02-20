package andrescaicedo.petagramrestapisincronizado.fragments;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.adaptadores.RecentMediaAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public interface IRecentMedia {

    public void generarGridLayout();
    public RecentMediaAdaptador crearAdaptadorTimeline(ArrayList<Mascota> mascota);
    public void iniciarAdaptadorRVTimeline(RecentMediaAdaptador adaptador);
}
