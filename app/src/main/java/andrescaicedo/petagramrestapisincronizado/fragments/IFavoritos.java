package andrescaicedo.petagramrestapisincronizado.fragments;

import andrescaicedo.petagramrestapisincronizado.adaptadores.MascotaAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

import java.util.ArrayList;

public interface IFavoritos {

    //Generando el Layout Lineal Vertical
    public void generarLinealLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

    public void generarToolbar();
}
