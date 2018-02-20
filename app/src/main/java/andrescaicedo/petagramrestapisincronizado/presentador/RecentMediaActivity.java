package andrescaicedo.petagramrestapisincronizado.presentador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.AcercaDe;
import andrescaicedo.petagramrestapisincronizado.ConfiguracionCuenta;
import andrescaicedo.petagramrestapisincronizado.ContactoForEmail;
import andrescaicedo.petagramrestapisincronizado.R;
import andrescaicedo.petagramrestapisincronizado.adaptadores.RecentMediaAdaptador;
import andrescaicedo.petagramrestapisincronizado.fragments.IRecentMedia;
import andrescaicedo.petagramrestapisincronizado.pojo.Followers;
import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;
import andrescaicedo.petagramrestapisincronizado.restApi.EndpointsApi;
import andrescaicedo.petagramrestapisincronizado.restApi.adapter.RestApiAdapter;
import andrescaicedo.petagramrestapisincronizado.restApi.model.TimelineResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecentMediaActivity extends AppCompatActivity implements IRecentMediaPresenter, IRecentMedia{

    private RecyclerView listaRecentMediaFollow;
    private static ArrayList<Mascota> mediaRecent;
    private String idFollow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recent_media);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
//        setSupportActionBar(miActionBar);
        if (miActionBar != null){
            setSupportActionBar(miActionBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Configuración de cuenta");
        }

        Followers follower = (Followers)getIntent().getExtras().getSerializable("parametro");
//        setIdFollow(getIntent().getExtras().getString("id_follow"));
        setIdFollow(follower.getId());

        TextView tvFotosDe = (TextView) this.findViewById(R.id.tvFotosDe);
        tvFotosDe.setText("Fotos de "+follower.getNombre());

        listaRecentMediaFollow = (RecyclerView) this.findViewById(R.id.rvRecentMediaFollow);
        listaRecentMediaFollow.setHasFixedSize(true);
    }

    @Override
    protected void onResume() {
        super.onResume();

//        setIdFollow(getIntent().getExtras().getString("id_follow"));
//        System.out.println("id en recentMedia2 "+getIdFollow());

        getRecentMediaFollower();
//        mostrarRecentMedioRV(getRecentMediaFollower());
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
                Intent intent2 = new Intent(RecentMediaActivity.this, ContactoForEmail.class);
                startActivity(intent2);
                break;
            case R.id.actionAcercaDe:
                Intent intent3 = new Intent(RecentMediaActivity.this, AcercaDe.class);
                startActivity(intent3);
                break;
            case R.id.actionConfigurarCuenta:
                Intent intent4 = new Intent(RecentMediaActivity.this, ConfiguracionCuenta.class);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //recyclerview
    //orden de llamado 5
    @Override
    public void generarGridLayout() {
//        LinearLayoutManager llm = new LinearLayoutManager(this);
//        llm.setOrientation(LinearLayoutManager.VERTICAL);
//        listaRecentMediaFollow.setLayoutManager(llm);

        RecyclerView.LayoutManager lManager = new GridLayoutManager(this, 3);
        listaRecentMediaFollow.setLayoutManager(lManager);
    }

    //orden de llamado 4
    @Override
    public RecentMediaAdaptador crearAdaptadorTimeline(ArrayList<Mascota> mascota) {
        RecentMediaAdaptador recentMediaAdaptador = new RecentMediaAdaptador(mascota,this);
        return recentMediaAdaptador;
    }

    //orden de llamado 3
    @Override
    public void iniciarAdaptadorRVTimeline(RecentMediaAdaptador adaptador) {
        listaRecentMediaFollow.setHasFixedSize(true);
        listaRecentMediaFollow.setAdapter(adaptador);
        listaRecentMediaFollow.getAdapter().notifyDataSetChanged();
    }

    //presentador

    //orden de llamado 2
    @Override
    public void mostrarRecentMedioRV(ArrayList<Mascota> mascota) {
        iniciarAdaptadorRVTimeline(crearAdaptadorTimeline(mascota));
        generarGridLayout();
    }

    //orden de llamado 1
    @Override
    public void getRecentMediaFollower() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gson = restApiAdapter.construyendoGsonDeserializadoTimeline();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionInicial(gson);

            Call<TimelineResponse> timelineResponseCall = endpointsApi.getRecentMediaTimeline(getIdFollow());
            timelineResponseCall.enqueue(new Callback<TimelineResponse>() {
                @Override
                public void onResponse(Call<TimelineResponse> call, Response<TimelineResponse> response) {
                    TimelineResponse timelineResponse = response.body();

                    ArrayList<Mascota> mascotas = timelineResponse.getMascotas();
                    if(mascotas!=null){
                        for (Mascota mascota:mascotas) {
                            mascota.setId_Cuenta(getIdFollow());
                        }
                        mostrarRecentMedioRV(mascotas);
                    }
                }

                @Override
                public void onFailure(Call<TimelineResponse> call, Throwable t) {
//                    mediaRecent = new ArrayList<>();
                    //Toast.makeText(context, "InicioPetPresent: " + t.toString(), Toast.LENGTH_SHORT).show();
                }
            });
    }

    public String getIdFollow() {
        return idFollow;
    }

    public void setIdFollow(String idFollow) {
        this.idFollow = idFollow;
    }

}
