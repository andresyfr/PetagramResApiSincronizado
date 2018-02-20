package andrescaicedo.petagramrestapisincronizado.db;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import andrescaicedo.petagramrestapisincronizado.pojo.Mascota;

public class ConstructorMascotas {

    private static final int LIKE = 1;
    Context context;

    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    //Primer Metodo obtener Mascotas Favoritas
    public ArrayList<Mascota> obtenerDatos(){

        BaseDatos db = new BaseDatos(context);

        return db.obtenerMascotasFavoritas();
    }

    //Metodo que nos ayudara a Insertar Mascota o si ya existe, agregarle un like mas
    public void insertarMascota(Mascota mascota){

        BaseDatos db = new BaseDatos(context);

        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, mascota.getNombre());
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_LIKES, LIKE);
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, mascota.getUrlFoto());

        db.insertarMascota(contentValues);
    }

    //Metodo que nos ayuda a verificar la Mascota
    public int verificarMascota(Mascota mascota){
        int verificarMascota;
        BaseDatos db = new BaseDatos(context);
        verificarMascota = db.verificarExisteMascotaBD(mascota);

        return verificarMascota;
    }

    //Metodo para Insertar Liken en Mascota
    public void insertarLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        db.obtenerMascotasFavoritas();
        db.insertarLikeMascota(mascota);
    }

    //Metodo para Mostrar likes de Mascota
    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        db.obtenerMascotasFavoritas();
        return db.obtenerLikesMascota(mascota);
    }

}
