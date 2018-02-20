package andrescaicedo.petagramrestapisincronizado.fragments;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.adaptadores.MascotaAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public interface IFavoritos {

    //Generando el Layout Lineal Vertical
    public void generarLinealLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

    public void generarToolbar();
}
