package andrescaicedo.petagramrestapisincronizado.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import andrescaicedo.petagramrestapisincronizado.AcercaDe;
import andrescaicedo.petagramrestapisincronizado.ConfiguracionCuenta;
import andrescaicedo.petagramrestapisincronizado.ContactoForEmail;
import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.adaptadores.MascotaAdaptador;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.presentador.FavoritosPresenter;
import andrescaicedo.petagramrestapisincronizado.presentador.IFavoritosPresenter;

import java.util.ArrayList;

public class Favoritos extends AppCompatActivity implements IFavoritos{

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private IFavoritosPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_perfil);


        listaMascotas = (RecyclerView) findViewById(R.id.rvPerfilCuenta);
        //presenter = new FavoritosPresenter(this, getBaseContext());
    }

    //11.- Seteando el Adaptador
    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }

    //12.- Inicializar Lista de Mascotas Hardcoded
    public void inicializarFavs(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota("Vamo a Calmarno", 15, "R.drawable.squar"));
        mascotas.add(new Mascota("Charmander", 12, "R.drawable.charmander"));
        mascotas.add(new Mascota("Hunter", 10, "R.drawable.hunter"));
        mascotas.add(new Mascota("Mew", 10, "R.drawable.mew"));
        mascotas.add(new Mascota("Ho-Oh", 7, "R.drawable.hooh"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favoritos, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.actionContacto:
                Intent intent2 = new Intent(Favoritos.this, ContactoForEmail.class);
                startActivity(intent2);
                break;
            case R.id.actionAcercaDe:
                Intent intent3 = new Intent(Favoritos.this, AcercaDe.class);
                startActivity(intent3);
                break;
            case R.id.actionConfigurarCuenta:
                Intent intent4 = new Intent(Favoritos.this, ConfiguracionCuenta.class);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void generarLinealLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public void generarToolbar() {
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.huella);
        //getSupportActionBar().setTitle(R.string.favs);
    }
}
