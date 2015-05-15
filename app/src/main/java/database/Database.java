package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import objeto.Aluno;

/**
 * Created by Diogo on 03/05/2015.
 */
public class Database extends SQLiteOpenHelper{
    private static final String TABLE_ALUNO = "aluno";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NOME = "nome";
    public static final String KEY_IDCURSO = "idCurso";
    public static final String KEY_FOTO = "foto";

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "AVALIACAOCADEIRAS";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table aluno (email text primary key, "
            + "nome text not null, idCurso integer not null, foto blob);";

    private SQLiteDatabase database;

    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE);
            database = db;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS aluno");
        onCreate(db);
    }

    public long insertAluno(Aluno aluno) {
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, aluno.getEmail());
        initialValues.put(KEY_NOME, aluno.getNome());
        initialValues.put(KEY_IDCURSO, aluno.getIdCurso());
        initialValues.put(KEY_FOTO, aluno.getFoto());
        return database.insert(TABLE_ALUNO, null, initialValues);
    }

    public Cursor getAllAlunos() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_ALUNO, new String[] { KEY_EMAIL, KEY_NOME,
                KEY_IDCURSO, KEY_FOTO }, null, null, null, null, null);
        return cursor;
    }

    public boolean updateNoticia(Aluno aluno) {
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, aluno.getEmail());
        initialValues.put(KEY_NOME, aluno.getNome());
        initialValues.put(KEY_IDCURSO, aluno.getIdCurso());
        initialValues.put(KEY_FOTO, aluno.getFoto());
        return database.update(TABLE_ALUNO, initialValues, KEY_EMAIL + "=" + aluno.getEmail(), null) > 0;
    }

    public void deleteAluno(Aluno aluno){
        database = this.getWritableDatabase();
        database.execSQL("delete from aluno where email = '" + aluno.getEmail() + "';");
    }

    public Aluno getAluno(String email){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_ALUNO, new String[] { KEY_EMAIL, KEY_NOME,
                KEY_IDCURSO, KEY_FOTO }, KEY_EMAIL + "=?", new String[] {email}, null, null, null);

        Aluno retorno = new Aluno();
        if(cursor.moveToNext()){
            retorno.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            retorno.setFoto(cursor.getBlob(cursor.getColumnIndex(KEY_FOTO)));
            retorno.setIdCurso(cursor.getInt(cursor.getColumnIndex(KEY_IDCURSO)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));
        }

        return retorno;
    }
}
