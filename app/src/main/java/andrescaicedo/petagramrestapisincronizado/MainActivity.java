package andrescaicedo.petagramrestapisincronizado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.adaptadores.PageAdapter;
import andrescaicedo.petagramrestapisincronizado.fragments.Favoritos;
import andrescaicedo.petagramrestapisincronizado.fragments.InicioPet;
import andrescaicedo.petagramrestapisincronizado.fragments.PerfilPet;
import andrescaicedo.petagramrestapisincronizado.restApi.adapter.RestApiAdapter;
import andrescaicedo.petagramrestapisincronizado.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String perfilInstagram="";
    private String idInstagram="";
    private PerfilPet perfilPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
//        setSupportActionBar(miActionBar);
        if (miActionBar != null){
            setSupportActionBar(miActionBar);
        }

        tabLayout   = (TabLayout) findViewById(R.id.tabLayout);
        viewPager   = (ViewPager) findViewById(R.id.viewPager);

        setUpPageViewer();

        obtenerPerfilShared();
        if (perfilInstagram.equals("")){
            crearPerfilShared();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Dando el Orden de los Fragments con el Siguiente Metodo
    private void setUpPageViewer(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_contacts);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }

    //Añandiendo los Fragments
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new InicioPet());
        perfilPet = new PerfilPet();
        fragments.add(perfilPet);

        return fragments;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.actionFavoritos:
                Intent intent = new Intent(MainActivity.this, Favoritos.class);
                startActivity(intent);
                break;
            case R.id.actionContacto:
                Intent intent2 = new Intent(MainActivity.this, ContactoForEmail.class);
                startActivity(intent2);
                break;
            case R.id.actionAcercaDe:
                Intent intent3 = new Intent(MainActivity.this, AcercaDe.class);
                startActivity(intent3);
                break;
            case R.id.actionConfigurarCuenta:
                Intent intent4 = new Intent(MainActivity.this, ConfiguracionCuenta.class);
                finish();
                startActivity(intent4);
                break;
            case R.id.actionRecibirNotificaciones:
                enviarToken(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void crearPerfilShared(){
        SharedPreferences perfilInstagram = getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = perfilInstagram.edit();
        editor.putString("perfilInstagram", "petyfr");
        editor.commit();

        SharedPreferences idInstagram = getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorId = idInstagram.edit();
        editorId.putString("idInstagram", "idPetyfr");
        editorId.commit();
    }

    private void obtenerPerfilShared(){
        SharedPreferences misReferencias = getSharedPreferences("shared", Context.MODE_PRIVATE);
        perfilInstagram = misReferencias.getString("perfilInstagram", "");

        idInstagram = misReferencias.getString("idInstagram", "");
    }

    //Nuevos mètodos firebase
    public void enviarToken(View v){
        String token= FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token, idInstagram);
    }

    private void enviarTokenRegistro(String token, String perfilInstagram){
        Log.d("Token",token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPIFirebase();
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuario(token,perfilInstagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();//aqui no es necesario un deserealizador por que la data es identica entonces se coje tal cual con body
                //Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("USUARIO_FIREBASE", usuarioResponse.getId_dispositivo());
                Log.d("USUARIO_INSTAGRAM", usuarioResponse.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });
    }

}
