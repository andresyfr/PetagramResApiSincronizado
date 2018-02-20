package andrescaicedo.petagramrestapisincronizado;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ConfiguracionCuenta extends AppCompatActivity {

    private TextInputEditText tiedtUsuario;
    private Button btnGuardarCuenta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion_cuenta);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
//        setSupportActionBar(miActionBar);
        if (miActionBar != null){
            setSupportActionBar(miActionBar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Configuración de cuenta");
        }

        tiedtUsuario = (TextInputEditText) findViewById(R.id.editTextConfiguarCuenta);
        btnGuardarCuenta = (Button) findViewById(R.id.buttonConfigurarCuenta);

        btnGuardarCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuarioInstagram();
            }
        });
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
                Intent intent2 = new Intent(ConfiguracionCuenta.this, ContactoForEmail.class);
                startActivity(intent2);
                break;
            case R.id.actionAcercaDe:
                Intent intent3 = new Intent(ConfiguracionCuenta.this, AcercaDe.class);
                startActivity(intent3);
                break;
            case R.id.actionConfigurarCuenta:
                Intent intent4 = new Intent(ConfiguracionCuenta.this, ConfiguracionCuenta.class);
                startActivity(intent4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void guardarUsuarioInstagram(){
        String usuario = tiedtUsuario.getText().toString();
        if (validarCampoInstagram(usuario)){
            SharedPreferences perfilInstagram = getSharedPreferences("shared", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = perfilInstagram.edit();
            editor.putString("perfilInstagram", usuario);
            editor.commit();

            Toast.makeText(this, "La cuenta '"+usuario.toString()+"' se guardo satisfactoriamente, regrese al perfil para observar los cambios!", Toast.LENGTH_LONG).show();
            this.finish();
            Intent intent1 = new Intent(ConfiguracionCuenta.this, MainActivity.class);
            startActivity(intent1);
            //Toast.makeText(this, R.string.account_savedreturn, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCampoInstagram(String usuario){
        if(usuario.isEmpty() || usuario == null || usuario.length() == 0)
            return false;
        else
            return true;
    }
}
