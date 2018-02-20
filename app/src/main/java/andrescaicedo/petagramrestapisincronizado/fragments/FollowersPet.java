package andrescaicedo.petagramrestapisincronizado.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosFollowersAdaptador;
import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosPerfilAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.presentador.FollowersPetPresenter;
import andrescaicedo.petagramrestapisincronizado.presentador.IFollowersPetPresenter;
import andrescaicedo.petagramrestapisincronizado.presentador.IPerfilPetPresenter;
import andrescaicedo.petagramrestapisincronizado.presentador.PerfilPetPresenter;


public class FollowersPet extends Fragment implements IFollowersPet {

    private RecyclerView listaFollowers;
    private RecyclerView.LayoutManager lManager;
    private CircularImageView circularImageView;
    private TextView tvNombrePerfil;
    private IFollowersPetPresenter followersPetPresenter;
    private RecyclerView.Adapter adapter;

    public FollowersPet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_followers, container, false);

        circularImageView = (CircularImageView)v.findViewById(R.id.imageViewFollowers);
        tvNombrePerfil = (TextView)v.findViewById(R.id.tvNombreFollowers);
        listaFollowers = (RecyclerView) v.findViewById(R.id.rvFollowersCuenta);
        listaFollowers.setHasFixedSize(true);
        followersPetPresenter = new FollowersPetPresenter(this, getContext(), obtenerProfileInstagram());

        return v;
    }

    private String obtenerProfileInstagram(){
        SharedPreferences misReferencias = this.getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        return misReferencias.getString("perfilInstagram", "");
    }

    @Override
    public void generarGridLayout() {
        lManager = new GridLayoutManager(getActivity(), 3);
        listaFollowers.setLayoutManager(lManager);
    }

    @Override
    public FotosFollowersAdaptador crearAdaptador(ArrayList<Followers> followers) {
        FotosFollowersAdaptador adaptador = new FotosFollowersAdaptador(followers, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(FotosFollowersAdaptador adapter) {
        listaFollowers.setHasFixedSize(true);
        listaFollowers.setAdapter(adapter);
    }

}
