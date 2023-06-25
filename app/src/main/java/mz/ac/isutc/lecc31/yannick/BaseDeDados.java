package mz.ac.isutc.lecc31.yannick;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDeDados extends SQLiteOpenHelper {

    private static String TABLE_NAME = "COMPUTADORES";
    private static String ID = "id";
    private static String MARCA = "marca";
    private static String MODELO = "modelo";
    public  static String  SERIE = "serie";
    public static String PROCESSADOR = "processador";
    public static String RAM = "ram";
    public static String HD = "hd";

    private static final String CREATE_TABLE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
                        ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        MARCA + " TEXT, " +
                        MODELO + " TEXT, " +
                        SERIE + " TEXT, " +
                        PROCESSADOR + " TEXT, " +
                        RAM + " INTEGER, " +
                        HD + " INTEGER)";

    public BaseDeDados(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }



    public long salvar(String marca, String modelo, String serie, String processador, int ram, int hd){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(MARCA, marca);
        valores.put(MODELO, modelo);
        valores.put(SERIE, serie);
        valores.put(PROCESSADOR, processador);
        valores.put(RAM, ram);
        valores.put(HD, hd);


        long insert = sqLiteDatabase.insert(TABLE_NAME, null, valores);
        sqLiteDatabase.close();

        return insert;
    }

    public ArrayList<Computador> ler(){
        ArrayList<Computador> computadores = new ArrayList<Computador>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);




        if (cursor.moveToFirst()) {
            do {

                if(Integer.parseInt(cursor.getString(5))>2){
                    computadores.add(new Computador(
                            Integer.parseInt(cursor.getString(0)),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            Integer.parseInt(cursor.getString(5)),
                            Integer.parseInt(cursor.getString(6))
                    ));
                }

            } while (cursor.moveToNext());

        }

        cursor.close();
        sqLiteDatabase.close();
        return computadores;
    }



    public void apagar(String nrserie) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME, "SERIE=?", new String[]{nrserie});
        sqLiteDatabase.close();
    }

    public void apagar(String[] nrserie) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.delete(TABLE_NAME, "SERIE=?", nrserie);
        sqLiteDatabase.close();
    }






}
