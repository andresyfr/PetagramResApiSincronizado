package andrescaicedo.petagramrestapisincronizado.restApi.model;

import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

import java.util.ArrayList;

public class PerfilResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
