package andrescaicedo.petagramrestapisincronizado.presentador;

import android.content.Context;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.db.ConstructorMascotas;
import andrescaicedo.petagramrestapisincronizado.fragments.IFavoritos;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public class FavoritosPresenter implements IFavoritosPresenter {

    private IFavoritos iFavoritos;
    private Context context;
    ConstructorMascotas constructorMascotas;
    ArrayList<Mascota> mascotas;

    public FavoritosPresenter(IFavoritos iFavoritos, Context context) {
        this.iFavoritos = iFavoritos;
        this.context = context;
        obtenerFavoritosBaseDatos();
    }

    @Override
    public void obtenerFavoritosBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarFavoritos();
    }

    @Override
    public void mostrarFavoritos() {
        iFavoritos.inicializarAdaptadorRV(iFavoritos.crearAdaptador(mascotas));
        iFavoritos.generarLinealLayoutVertical();
        iFavoritos.generarToolbar();
    }
}
