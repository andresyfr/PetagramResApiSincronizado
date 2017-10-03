package andrescaicedo.petagramrestapisincronizado.fragments;

import andrescaicedo.petagramrestapisincronizado.adaptadores.MascotaAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

import java.util.ArrayList;

public interface IInicioPet {

    public void generarGridLayout();
    public MascotaAdaptador crearAdaptadorTimeline(ArrayList<Mascota> mascota);
    public void iniciarAdaptadorRVTimeline(MascotaAdaptador adaptador);
}
