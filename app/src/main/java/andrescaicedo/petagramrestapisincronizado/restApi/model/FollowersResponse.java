package andrescaicedo.petagramrestapisincronizado.restApi.model;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.pojo.Followers;

public class FollowersResponse {
   ArrayList<Followers> cuenta;

    public ArrayList<Followers> getCuenta() {
        return cuenta;
    }

    public void setCuenta(ArrayList<Followers> cuenta) {
        this.cuenta = cuenta;
    }
}
