package andrescaicedo.petagramrestapisincronizado.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosFollowersAdaptador;
import andrescaicedo.petagramrestapisincronizado.adaptadores.MascotaAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.presentador.IInicioPetPresenter;
import andrescaicedo.petagramrestapisincronizado.presentador.InicioPetPresenter;

public class InicioPet extends Fragment implements IInicioPet {

    private RecyclerView.LayoutManager lManager;
    ArrayList<Followers> followers;
    private RecyclerView listaFollowers;
    private IInicioPetPresenter followersPresenter;

    public InicioPet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        listaFollowers = (RecyclerView) v.findViewById(R.id.rvFollowers);
        listaFollowers.setHasFixedSize(true);

        followersPresenter = new InicioPetPresenter(this, getContext());



        //AQUI HAY QUE MANDAR EL PRESENTADOR

        //inicializarListaMascotas();
        //inicializarAdaptador();


        return v;
    }

//    public void inicializarListaMascotas(){
//        followers = new ArrayList<>();
//        followers.add(new Followers("1","as","as","as"));
//    }

    /*
    //11.- Seteando el Adaptador
    public void inicializarAdaptador(){
        FotosFollowersAdaptador adaptador = new FotosFollowersAdaptador(followers, getActivity());
        listaFollowers.setAdapter(adaptador);
    }
    */

    /*
    //12.- Inicializar Lista de Mascotas
    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Charmander", 0, "R.drawable.charmander"));
        mascotas.add(new Mascota("Chilaquill", 0, "R.drawable.chilaquil"));
        mascotas.add(new Mascota("Elefantito", 0, "R.drawable.donphant"));
        mascotas.add(new Mascota("Ho-Oh", 0, "R.drawable.hooh"));
        mascotas.add(new Mascota("Vamo a Calmarno", 0, "R.drawable.squar"));
        mascotas.add(new Mascota("Hunter", 0, "R.drawable.hunter"));
        mascotas.add(new Mascota("Gatito", 0, "R.drawable.meowth"));
        mascotas.add(new Mascota("Mew", 0, "R.drawable.mew"));
    }
    */

    @Override
    public void generarGridLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaFollowers.setLayoutManager(llm);
    }

    @Override
    public FotosFollowersAdaptador crearAdaptadorTimeline(ArrayList<Followers> followers) {
        FotosFollowersAdaptador adaptador = new FotosFollowersAdaptador(followers, getActivity());
        return adaptador;
    }

    @Override
    public void iniciarAdaptadorRVTimeline(FotosFollowersAdaptador adaptador) {
        listaFollowers.setHasFixedSize(true);
        listaFollowers.setAdapter(adaptador);
    }
}
