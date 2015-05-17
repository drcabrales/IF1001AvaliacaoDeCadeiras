package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import objeto.Aluno;
import objeto.Faculdade;
import objeto.Curso;
import objeto.Avaliacao;
import objeto.AvaliacaoCategoria;
import objeto.MetodoAvaliacaoCadeira;
import objeto.CategoriaAvaliacaoCadeira;
import objeto.AvaliacaoMetodo;
import objeto.Comentario;

/**
 * Created by Diogo on 03/05/2015.
 */
public class Database extends SQLiteOpenHelper{
    private static final String TABLE_ALUNO = "aluno";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_NOME = "nome";
    public static final String KEY_IDCURSO = "idCurso";
    public static final String KEY_FOTO = "foto";

    private static  final String TABLE_FACULDADE = "faculdade";
    public static final String KEY_SIGLA = "sigla";

    private static final String TABLE_CURSO = "curso";
    public static final String KEY_IDFACULDADE = "idFaculdade";
    public static final String KEY_DESCRICAO = "descricao";
    public static final String KEY_NOMECURSO = "nomeCurso";

    private static  final String TABLE_CATEGORIAAVALIACAOCADEIRA = "categoriaAvaliacaoCadeira";

    private static  final String TABLE_METODOAVALIACAOCADEIRA = "metodoAvaliacaoCadeira";

    private static  final String TABLE_AVALIACAO = "avaliacao";
    public static final String KEY_ID = "id";
    public static final String KEY_EMAILALUNO = "emailAluno";

    private static  final String TABLE_AVALIACAOCATEGORIA = "avaliacaoCategoria";
    public static final String KEY_IDAVALIACAO = "idAvaliacao";
    public static final String KEY_IDCATEGORIA = "idCategoria";
    public static final String KEY_NOTA = "nota";

    private static  final String TABLE_AVALIACAOMETODO = "avaliacaoMetodo";
    public static final String KEY_IDMETODO = "idMetodo";

    private static  final String TABLE_COMENTARIO = "comentario";
    public static final String KEY_ANO = "ano";
    public static final String KEY_SEMESTRE = "semestre";
    public static final String KEY_TEXT = "text";

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "AVALIACAOCADEIRAS";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table faculdade (id integer primary key, nome text not null, sigla text not null );"+
            "create table curso (sigla text not null, nome text not null, idFaculdade integer not null, PRIMARY KEY (nome, idFaculdade), FOREIGN KEY(idFaculdade) REFERENCES faculdade(id));"+
            "create table aluno (email text primary key,nome text not null,foto blob, nomeCurso text not null, idFaculdade integer not null, FOREIGN KEY(nomeCurso) REFERENCES curso(nome),FOREIGN KEY(idFaculdade) REFERENCES faculdade(id));"+
            "create table categoriaAvaliacaoCadeira (id integer not null, nome text not null, PRIMARY KEY autoincrement (id) );"+
            "create table metodoAvaliacaoCadeira (id integer not null, nome text not null, PRIMARY KEY autoincrement (id) );"+
            "create table avaliacao (id integer not null, emailAluno text not null, nomeCurso text not null, idFaculdade integer not null, FOREIGN KEY(nomeCurso) REFERENCES curso(nome), FOREIGN KEY(idFaculdade) REFERENCES faculdade(id), PRIMARY KEY autoincrement (id) );"+
            "create table avaliacaoMetodo (idAvaliacao integer not null, idMetodo integer not null , PRIMARY KEY (idAvaliacao, idMetodo ), FOREIGN KEY(idMetodo) REFERENCES metodoAvaliacaoCadeira(id), FOREIGN KEY(idAvaliacao) REFERENCES avaliacao (id));"+
            "create table avaliacaoCategoria (idAvaliacao integer not null, idCategoria integer not null, nota text not null, PRIMARY KEY (idAvaliacao, idCategoria ), FOREIGN KEY(idCategoria) REFERENCES categoriaAvaliacaoCadeira(id), FOREIGN KEY(idAvaliacao) REFERENCES avaliacao (id));"+
            "create table comentario (id integer not null, emailAluno text not null, ano text not null, semestre text not null, text text not null, PRIMARY KEY autoincrement (id))"

            ;



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
        db.execSQL("DROP TABLE IF EXISTS faculdade"+"DROP TABLE IF EXIST curso"+"DROP TABLE IF EXIST aluno"+"DROP TABLE IF EXIST categoriaAvaliacaoCadeira"+"DROP TABLE IF EXIST avaliacao"+" DROP TABLE IF EXIST avaliacaoMetodo"+ "DROP TABLE IF EXIST avaliacaoCategoria"+"DROP TABLE IF EXIST comentario" );
        onCreate(db);
    }

    public long insertFaculdade (Faculdade faculdade ){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, faculdade.getId());
        initialValues.put(KEY_NOME, faculdade.getNome());
        initialValues.put(KEY_SIGLA, faculdade.getSigla());
        return database.insert(TABLE_FACULDADE, null, initialValues);
    }

    public long insertCurso(Curso curso){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOME, curso.getNome());
        initialValues.put(KEY_IDFACULDADE, curso.getIdFaculdade());
        initialValues.put(KEY_DESCRICAO, curso.getDescricao());
        return database.insert(TABLE_CURSO, null, initialValues);
    }

    public long insertAluno(Aluno aluno) {
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, aluno.getEmail());
        initialValues.put(KEY_NOME, aluno.getNome());
        initialValues.put(KEY_FOTO, aluno.getFoto());
        initialValues.put(KEY_NOMECURSO, aluno.getNomeCurso());
        initialValues.put(KEY_IDFACULDADE, aluno.getIdFaculdade());
        return database.insert(TABLE_ALUNO, null, initialValues);
    }

    public long insertCategoriaAvaliacaoCadeira(CategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, categoriaAvaliacaoCadeira.getId());
        initialValues.put(KEY_NOME, categoriaAvaliacaoCadeira.getNome());
        return database.insert(TABLE_CATEGORIAAVALIACAOCADEIRA, null, initialValues);
    }

    public long insertMetodoAvaliacaoCadeira(MetodoAvaliacaoCadeira metodoAvaliacaoCadeira){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, metodoAvaliacaoCadeira.getId());
        initialValues.put(KEY_NOME, metodoAvaliacaoCadeira.getNome());
        return database.insert(TABLE_METODOAVALIACAOCADEIRA, null, initialValues);
    }

    public long insertAvaliacao(Avaliacao avaliacao){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, avaliacao.getId());
        initialValues.put(KEY_EMAILALUNO, avaliacao.getEmailAluno());
        initialValues.put(KEY_NOMECURSO, avaliacao.getNomeCurso());
        initialValues.put(KEY_IDFACULDADE, avaliacao.getIdFaculdade());
        return database.insert(TABLE_AVALIACAO, null, initialValues);
    }

    public long insertAvaliacaoMetodo(AvaliacaoMetodo avaliacaoMetodo){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_IDAVALIACAO, avaliacaoMetodo.getIdAvaliacao());
        initialValues.put(KEY_IDMETODO, avaliacaoMetodo.getIdMetodo());
        return database.insert(TABLE_AVALIACAOMETODO, null, initialValues);
    }

    public long insertAvaliacaoCategoria(AvaliacaoCategoria avaliacaoCategoria){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_IDAVALIACAO, avaliacaoCategoria.getIdAvaliacao());
        initialValues.put(KEY_IDCATEGORIA, avaliacaoCategoria.getIdCategoria());
        initialValues.put(KEY_NOTA, avaliacaoCategoria.getNota());
        return database.insert(TABLE_AVALIACAOCATEGORIA, null, initialValues);
    }

    public long insertComentario(Comentario Comentario){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, Comentario.getId());
        initialValues.put(KEY_EMAILALUNO, Comentario.getEmailAluno());
        initialValues.put(KEY_ANO, Comentario.getAno());
        initialValues.put(KEY_SEMESTRE, Comentario.getSemestre());
        initialValues.put(KEY_TEXT, Comentario.getText());
        return database.insert(TABLE_COMENTARIO, null, initialValues);
    }

    public Cursor getAllAlunos() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_ALUNO, new String[] { KEY_EMAIL, KEY_NOME,
                 KEY_FOTO, KEY_NOMECURSO, KEY_IDFACULDADE }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllFaculdades() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_FACULDADE, new String[] { KEY_ID, KEY_NOME,
                KEY_SIGLA }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllCurso() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_CURSO, new String[] { KEY_NOME, KEY_IDFACULDADE,
                KEY_DESCRICAO }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllCategoriaAvaliacaoCadeira() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_AVALIACAOCATEGORIA, new String[] { KEY_ID, KEY_NOME }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllMetodoAvaliacaoCadeira() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_METODOAVALIACAOCADEIRA, new String[] { KEY_ID, KEY_NOME }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllAvaliacao() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_AVALIACAO, new String[] { KEY_ID, KEY_EMAILALUNO, KEY_NOMECURSO, KEY_IDFACULDADE }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllAvaliacaoMetodo() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_AVALIACAOMETODO, new String[] { KEY_IDAVALIACAO, KEY_IDMETODO }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllAvaliacaoCategoria() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_AVALIACAOCATEGORIA, new String[] { KEY_IDAVALIACAO, KEY_IDCATEGORIA, KEY_NOTA }, null, null, null, null, null);
        return cursor;
    }

    public Cursor getAllComentario() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_COMENTARIO, new String[] { KEY_ID, KEY_EMAILALUNO, KEY_ANO, KEY_SEMESTRE, KEY_TEXT }, null, null, null, null, null);
        return cursor;
    }

    public boolean updateNoticia(Aluno aluno) {
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAIL, aluno.getEmail());
        initialValues.put(KEY_NOME, aluno.getNome());
        initialValues.put(KEY_FOTO, aluno.getFoto());
        initialValues.put(KEY_NOMECURSO, aluno.getNomeCurso());
        initialValues.put(KEY_IDFACULDADE, aluno.getIdFaculdade());
        return database.update(TABLE_ALUNO, initialValues, KEY_EMAIL + "=" + aluno.getEmail(), null) > 0;
    }

    public void deleteAluno(Aluno aluno){
        database = this.getWritableDatabase();
        database.execSQL("delete from aluno where email = '" + aluno.getEmail() + "';");
    }

    public void deleteFaculdade(Faculdade faculdade){
        database = this.getWritableDatabase();
        database.execSQL("delete from faculdade where id = '" + faculdade.getId() + "';");
    }

    public void deleteCurso(Curso curso){
        database = this.getWritableDatabase();
        database.execSQL("delete from curso where nome = '" + curso.getNome() + "';");
    }

    public void deleteCategoriaAvaliacaoCadeira(CategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira){
        database = this.getWritableDatabase();
        database.execSQL("delete from categoriaAvaliacaoCadeira where id = '" + categoriaAvaliacaoCadeira.getId() + "';");
    }

    public void deleteMetodoAvaliacaoCadeira(MetodoAvaliacaoCadeira metodoAvaliacaoCadeira){
        database = this.getWritableDatabase();
        database.execSQL("delete from metodoAvaliacaoCadeira where id = '" + metodoAvaliacaoCadeira.getId() + "';");
    }

    public void deleteAvaliacao(Avaliacao avaliacao){
        database = this.getWritableDatabase();
        database.execSQL("delete from avaliacao where id = '" + avaliacao.getId() + "';");
    }

    public void deleteAvaliacaoMetodo(AvaliacaoMetodo avaliacaoMetodo){
        database = this.getWritableDatabase();
        database.execSQL("delete from avaliacaoMetodo where id = '" + avaliacaoMetodo.getIdAvaliacao() + "';");
    }

    public void deleteAvaliacaoCategoria(AvaliacaoCategoria avaliacaoCategoria){
        database = this.getWritableDatabase();
        database.execSQL("delete from avaliacaoCategoria where id = '" + avaliacaoCategoria.getIdAvaliacao() + "';");
    }

    public void deleteComentario(Comentario comentario){
        database = this.getWritableDatabase();
        database.execSQL("delete from comentario where id = '" + comentario.getId() + "';");
    }

    public Aluno getAluno(String email){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_ALUNO, new String[] { KEY_EMAIL, KEY_NOME,
                 KEY_FOTO, KEY_NOMECURSO, KEY_IDFACULDADE }, KEY_EMAIL + "=?", new String[] {email}, null, null, null);

        Aluno retorno = new Aluno();
        if(cursor.moveToNext()){
            retorno.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            retorno.setFoto(cursor.getBlob(cursor.getColumnIndex(KEY_FOTO)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));
            retorno.setNomeCurso(cursor.getString(cursor.getColumnIndex(KEY_NOMECURSO)));
            retorno.setIdFaculdade(cursor.getInt(cursor.getColumnIndex(KEY_IDFACULDADE)));
        }

        return retorno;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////
//problema aqui pois id é inteiro

    public Faculdade getFaculdade(int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_FACULDADE, new String[] { KEY_ID, KEY_NOME,
                KEY_SIGLA}, KEY_ID + "=?", new String[] {id}, null, null, null);

        Faculdade retorno = new Faculdade();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));
            retorno.setSigla(cursor.getString(cursor.getColumnIndex(KEY_SIGLA)));

        }

        return retorno;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Curso getCurso(String nome){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_CURSO, new String[] { KEY_NOME, KEY_IDFACULDADE,
                KEY_DESCRICAO}, KEY_NOME + "=?", new String[] {nome}, null, null, null);

        Curso retorno = new Curso();
        if(cursor.moveToNext()){
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));
            retorno.setIdFaculdade(cursor.getInt(cursor.getColumnIndex(KEY_IDFACULDADE)));
            retorno.setDescricao(cursor.getString(cursor.getColumnIndex(KEY_DESCRICAO)));

        }

        return retorno;
    }
////////////////////////////////////////////////////////mesmo pro de faculdade int id
    public CategoriaAvaliacaoCadeira getCategoriaAvaliacaoCadeira(int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_CATEGORIAAVALIACAOCADEIRA, new String[] { KEY_ID, KEY_NOME
               }, KEY_ID + "=?", new String[] {id}, null, null, null);

        CategoriaAvaliacaoCadeira retorno = new CategoriaAvaliacaoCadeira();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));

        }

        return retorno;
    }
 ////////////////////////////////////////mesmo pro de faculdade int id

    public MetodoAvaliacaoCadeira getMetodoAvaliacaoCadeira(int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_METODOAVALIACAOCADEIRA, new String[] { KEY_ID, KEY_NOME
        }, KEY_ID + "=?", new String[] {id}, null, null, null);

        MetodoAvaliacaoCadeira retorno = new MetodoAvaliacaoCadeira();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));

        }

        return retorno;
    }
 ///////////////////////////////////////mesmo pro int

    public Avaliacao getAvaliacao(int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_AVALIACAO, new String[] { KEY_ID, KEY_EMAILALUNO, KEY_NOMECURSO, KEY_IDFACULDADE
        }, KEY_ID + "=?", new String[] {id}, null, null, null);

        Avaliacao retorno = new Avaliacao();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setEmailAluno(cursor.getString(cursor.getColumnIndex(KEY_NOME)));
            retorno.setNomeCurso(cursor.getString(cursor.getColumnIndex(KEY_NOMECURSO)));
            retorno.setIdFaculdade(cursor.getInt(cursor.getColumnIndex(KEY_IDFACULDADE)));

        }

        return retorno;
    }


    /////////////////////////////////////////mesmo pro int id

    public AvaliacaoMetodo getAvaliacaoMetodo(int idAvaliacao){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_AVALIACAOMETODO, new String[] { KEY_IDAVALIACAO, KEY_IDMETODO
        }, KEY_IDAVALIACAO + "=?", new String[] {idAvaliacao}, null, null, null);

        AvaliacaoMetodo retorno = new AvaliacaoMetodo();
        if(cursor.moveToNext()){
            retorno.setIdAvaliacao(cursor.getInt(cursor.getColumnIndex(KEY_IDAVALIACAO)));
            retorno.setIdMetodo(cursor.getInt(cursor.getColumnIndex(KEY_IDMETODO)));


        }

        return retorno;
    }
//////////////////////// zzzz mesmo pro

    public AvaliacaoCategoria getAvaliacaoCategoria(int idAvaliacao){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_AVALIACAOCATEGORIA, new String[] { KEY_IDAVALIACAO, KEY_IDCATEGORIA, KEY_NOTA
        }, KEY_IDAVALIACAO + "=?", new String[] {idAvaliacao}, null, null, null);

        AvaliacaoCategoria retorno = new AvaliacaoCategoria();
        if(cursor.moveToNext()){
            retorno.setIdAvaliacao(cursor.getInt(cursor.getColumnIndex(KEY_IDAVALIACAO)));
            retorno.setIdCategoria(cursor.getInt(cursor.getColumnIndex(KEY_IDCATEGORIA)));
            retorno.setNota(cursor.getString(cursor.getColumnIndex(KEY_NOTA)));

        }

        return retorno;
    }
///////////////////////////////////////////mesmo pro

    public Comentario getComentario(int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.query(TABLE_COMENTARIO, new String[] { KEY_ID, KEY_EMAILALUNO, KEY_ANO, KEY_SEMESTRE, KEY_TEXT
        }, KEY_ID + "=?", new String[] {id}, null, null, null);

        Comentario retorno = new Comentario();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_IDAVALIACAO)));
            retorno.setEmailAluno(cursor.getString(cursor.getColumnIndex(KEY_EMAILALUNO)));
            retorno.setAno(cursor.getString(cursor.getColumnIndex(KEY_ANO)));
            retorno.setSemestre(cursor.getString(cursor.getColumnIndex(KEY_SEMESTRE)));
            retorno.setText(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));

        }

        return retorno;
    }


}
