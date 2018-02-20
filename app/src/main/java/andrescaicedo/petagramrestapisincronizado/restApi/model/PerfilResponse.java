package andrescaicedo.petagramrestapisincronizado.restApi.model;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public class PerfilResponse {

    ArrayList<Mascota> mascotas;

    public ArrayList<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(ArrayList<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}
