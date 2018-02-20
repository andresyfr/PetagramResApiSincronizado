package andrescaicedo.petagramrestapisincronizado;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
//        setSupportActionBar(miActionBar);
        if (miActionBar != null){
            setSupportActionBar(miActionBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
                Intent intent2 = new Intent(AcercaDe.this, ContactoForEmail.class);
                startActivity(intent2);
                break;
            case R.id.actionAcercaDe:
                break;
            case R.id.actionConfigurarCuenta:
                Intent intent4 = new Intent(AcercaDe.this, ConfiguracionCuenta.class);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
