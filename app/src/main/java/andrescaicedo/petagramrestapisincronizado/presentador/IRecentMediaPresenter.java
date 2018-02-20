package andrescaicedo.petagramrestapisincronizado.presentador;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public interface IRecentMediaPresenter {
    public void mostrarRecentMedioRV(ArrayList<Mascota> mascota);
    public void getRecentMediaFollower();
}
