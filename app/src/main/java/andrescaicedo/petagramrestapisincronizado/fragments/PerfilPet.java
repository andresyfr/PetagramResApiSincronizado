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

import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.adaptadores.FotosPerfilAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.presentador.IPerfilPetPresenter;
import andrescaicedo.petagramrestapisincronizado.presentador.PerfilPetPresenter;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class PerfilPet extends Fragment implements IPerfilPet {

    private RecyclerView listaMascotas;
    private RecyclerView.LayoutManager lManager;
    private CircularImageView circularImageView;
    private TextView tvNombrePerfil;
    private IPerfilPetPresenter perfilPresentador;
    private RecyclerView.Adapter adapter;

    ArrayList<Mascota> mascotas;


    public PerfilPet() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        circularImageView = (CircularImageView)v.findViewById(R.id.imageViewPerfil);
        tvNombrePerfil = (TextView)v.findViewById(R.id.tvNombrePerfil);
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvPerfilCuenta);
        listaMascotas.setHasFixedSize(true);
        perfilPresentador = new PerfilPetPresenter(this, getContext(), obtenerProfileInstagram());

        return v;
    }

    private String obtenerProfileInstagram(){
        SharedPreferences misReferencias = this.getActivity().getSharedPreferences("shared", Context.MODE_PRIVATE);
        return misReferencias.getString("perfilInstagram", "");
    }

    @Override
    public void generarGridLayout() {
        lManager = new GridLayoutManager(getActivity(), 3);
        listaMascotas.setLayoutManager(lManager);
    }

    @Override
    public FotosPerfilAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        FotosPerfilAdaptador adaptador = new FotosPerfilAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(FotosPerfilAdaptador adapter) {
        listaMascotas.setHasFixedSize(true);
        listaMascotas.setAdapter(adapter);
    }

    @Override
    public void crearImagenPerfil(Followers profileUser) {
        Picasso.with(getActivity())
                .load(profileUser.getUrlFotoPerfil())
                .placeholder(R.drawable.huella)
                .into(circularImageView);

        tvNombrePerfil.setText(profileUser.getNombre());
    }
}
