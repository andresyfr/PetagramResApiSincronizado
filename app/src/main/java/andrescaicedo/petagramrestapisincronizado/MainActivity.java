package andrescaicedo.petagramrestapisincronizado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
import andrescaicedo.petagramrestapisincronizado.fragments.FollowersPet;
import andrescaicedo.petagramrestapisincronizado.fragments.InicioPet;
import andrescaicedo.petagramrestapisincronizado.fragments.PerfilPet;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;
import andrescaicedo.petagramrestapisincronizado.restApi.adapter.RestApiAdapter;
import andrescaicedo.petagramrestapisincronizado.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String perfilInstagram="";
    private String idInstagram="";

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

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_name);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_contacts);
    }

    //Añandiendo los Fragments
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        //fragments.add(new InicioPet());


        fragments.add(new PerfilPet());
        fragments.add(new InicioPet());

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
            default: break;

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
        System.out.println("====El token"+FirebaseInstanceId.getInstance().getToken());
    }

    //Nuevos mètodos firebase segundo curso
    public void enviarToken(View v){
//        actualizar id instagram
        obtenerPerfilShared();
        String token= FirebaseInstanceId.getInstance().getToken();
        Log.d("Token en enviar token",token);
        enviarTokenRegistro(token, idInstagram);
    }

    private UsuarioResponse usuario=new UsuarioResponse("-KzUjSdvghTqH6snJ3oo","dixRf-v3RoE:APA91bGtH-L_o4do8SNC8UqPl_qN70F1PHTSSU2pWkPNXDHxFREFuY8Qq17tLY7NrLRcQA3S7JlFZPo_NlGfycXrBmdFHv-B33bSxFVNdSxUk30t-4EmZWNWQRQcVvoEhBGcFUo-7bWm","5738216068");

    private void enviarTokenRegistro(final String token, String id_usuario_instagram){
        Log.d("Token",token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestAPIFirebase();
        //                                                                             TOKEN, ANIMAL_EMISOR
        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarUsuarioFirebase(token,id_usuario_instagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                usuario = response.body();//aqui no es necesario un deserealizador por que la data es identica entonces se coje tal cual con body
                //Log.d("ID_FIREBASE", usuarioResponse.getId());
                crearIdAutoShared(usuario.getId_auto(),token);
                Log.d("USER_FIREBASE_REGISTRAD", usuario.getId_auto()+" - "+usuario.getId_dispositivo());
//                Log.d("USUARIO_INSTAGRAM", usuario.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d("5.0=========", call.toString()+"=="+t.toString());
            }
        });
    }

    public void crearIdAutoShared(String idAutoGenerado, String idDispositivo){
        SharedPreferences idAuto = getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = idAuto.edit();
        editor.putString("idAuto", idAutoGenerado);
        editor.commit();

        SharedPreferences idDisposit = getSharedPreferences("shared", Context.MODE_PRIVATE);
        SharedPreferences.Editor editorDis = idDisposit.edit();
        editorDis.putString("idDispositivo", idDispositivo);
        editorDis.commit();
    }

    //Nuevos mètodos firebase tercer curso
    public void likeFirebase(View v){
        Log.d("TOQUE_ANIMAL", "true");
        Log.d("0 =================", "bandera firebase");
        Log.d("1 =================", "bandera firebase");
        Log.d("2 =================", "bandera firebase");
        Log.d("3 =================", "bandera firebase");
//        SharedPreferences misReferencias = getSharedPreferences("shared", Context.MODE_PRIVATE);
//        perfilInstagram = misReferencias.getString("idAuto", "");
//        Log.d("4 =================", "bandera firebase");
//        //final UsuarioResponse usuarioResponse = new UsuarioResponse("-KzvjzG8jsx4XIyqJD8J", usuario.getId_dispositivo(), usuario.getId_usuario_instagram());
//
//        final String receptor = "receptor";
//        final String emisor = "emisor";
//
//        RestApiAdapter restApiAdapter =  new RestApiAdapter();
//        EndpointsApi endponits = restApiAdapter.establecerConexionRestAPIFirebase();
//        //                                                                 ID_receptor = id pequeño firebase          ID_INSTAGRAM_EMISOR
//        Call<UsuarioResponse> usuarioResponseCall = endponits.likeMascotaFirebase(usuarioResponse.getId(), usuario.getId_usuario_instagram()) ;
//        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
//            @Override
//            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
//                UsuarioResponse usuarioResponse1 =response.body();
//                //Log.d("ID_FIREBASE", usuarioResponse1.getId());
//                Log.d("ID_TOKEN_FIREBASE", usuarioResponse1.getId_dispositivo());
//                //Log.d("like_ANIMAL_FIREBASE", usuarioResponse1.getId_foto_instagram());
//            }
//
//            @Override
//            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
//                Log.d("5=========", call.toString()+"=="+t.toString());
//            }
//        });
    }
}
