package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;


import objeto.Aluno;
import objeto.Cadeira;
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

    public static final String TABLE_CADEIRA = "cadeira";
    public static final String KEY_NOMEPROFESSOR = "nomeProfessor";

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
    public static final String KEY_NOMECADEIRA = "nomeCadeira";

    private static  final String TABLE_AVALIACAOCATEGORIA = "avaliacaoCategoria";
    public static final String KEY_IDAVALIACAO = "idAvaliacao";
    public static final String KEY_IDCATEGORIA = "idCategoria";
    public static final String KEY_NOTA = "nota";

    private static  final String TABLE_AVALIACAOMETODO = "avaliacaoMetodo";
    public static final String KEY_IDMETODO = "idMetodo";

    private static  final String TABLE_COMENTARIO = "comentario";
    public static final String KEY_ANO = "ano";
    public static final String KEY_SEMESTRE = "semestre";
    public static final String KEY_TEXT = "texto";

    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "AVALIACAOCADEIRAS";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE_FACULDADE =
            "create table faculdade (id integer primary key autoincrement, nome text not null, sigla text not null );";

    private static final String DATABASE_CREATE_CURSO =
            "create table curso (nome text not null, idFaculdade integer not null, descricao text not null, primary key (nome, idFaculdade), FOREIGN KEY(idFaculdade) REFERENCES faculdade(id));";

    private static final String DATABASE_CREATE_ALUNO =
            "create table aluno (email text primary key,nome text not null,foto blob, nomeCurso text not null, idFaculdade integer not null, FOREIGN KEY(nomeCurso) REFERENCES curso(nome),FOREIGN KEY(idFaculdade) REFERENCES faculdade(id));";

    private static final String DATABASE_CREATE_CATEGORIAAVALIACAOCADEIRA =
            "create table categoriaAvaliacaoCadeira (id integer primary key autoincrement, nome text not null);";

    private static final String DATABASE_CREATE_METODOAVALIACAOCADEIRA =
            "create table metodoAvaliacaoCadeira (id integer primary key autoincrement, nome text not null);";

    private static final String DATABASE_CREATE_CADEIRA =
            "create table cadeira (nome text not null, nomeProfessor text not null, nomeCurso text not null, idFaculdade integer not null, primary key (nome, nomeProfessor, nomeCurso, idFaculdade), FOREIGN KEY(nomeCurso) REFERENCES curso(nome),FOREIGN KEY(idFaculdade) REFERENCES faculdade(id))";

    private static final String DATABASE_CREATE_AVALIACAO =
            "create table avaliacao (id integer primary key autoincrement, emailAluno text not null, nomeCurso text not null, idFaculdade integer not null, nomeCadeira text not null, nomeProfessor text not null, FOREIGN KEY(nomeCurso) REFERENCES curso(nome), FOREIGN KEY(idFaculdade) REFERENCES faculdade(id), FOREIGN KEY(nomeCadeira) REFERENCES cadeira(nome), FOREIGN KEY(nomeProfessor) REFERENCES cadeira(nomeProfessor) );";

    private static final String DATABASE_CREATE_AVALIACAOMETODO =
            "create table avaliacaoMetodo (idAvaliacao integer not null, idMetodo integer not null, PRIMARY KEY (idAvaliacao, idMetodo ), FOREIGN KEY(idMetodo) REFERENCES metodoAvaliacaoCadeira(id), FOREIGN KEY(idAvaliacao) REFERENCES avaliacao (id));";

    private static final String DATABASE_CREATE_AVALIACAOCATEGORIA =
            "create table avaliacaoCategoria (idAvaliacao integer not null, idCategoria integer not null, nota integer not null, PRIMARY KEY (idAvaliacao, idCategoria ), FOREIGN KEY(idCategoria) REFERENCES categoriaAvaliacaoCadeira(id), FOREIGN KEY(idAvaliacao) REFERENCES avaliacao (id));";

    private static final String DATABASE_CREATE_COMENTARIO =
            "create table comentario (id integer primary key autoincrement, idAvaliacao integer not null, ano text not null, semestre text not null, texto text not null, FOREIGN KEY(idAvaliacao) REFERENCES avaliacao(id))";


    private SQLiteDatabase database;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE_FACULDADE);
            db.execSQL(DATABASE_CREATE_CURSO);
            db.execSQL(DATABASE_CREATE_ALUNO);
            db.execSQL(DATABASE_CREATE_CATEGORIAAVALIACAOCADEIRA);
            db.execSQL(DATABASE_CREATE_METODOAVALIACAOCADEIRA);
            db.execSQL(DATABASE_CREATE_CADEIRA);
            db.execSQL(DATABASE_CREATE_AVALIACAO);
            db.execSQL(DATABASE_CREATE_AVALIACAOMETODO);
            db.execSQL(DATABASE_CREATE_AVALIACAOCATEGORIA);
            db.execSQL(DATABASE_CREATE_COMENTARIO);
            database = db;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(TAG, oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS faculdade;"+"DROP TABLE IF EXIST curso;"+"DROP TABLE IF EXIST aluno;"+"DROP TABLE IF EXIST categoriaAvaliacaoCadeira;"+"DROP TABLE IF EXIST avaliacao;"+" DROP TABLE IF EXIST avaliacaoMetodo;"+ "DROP TABLE IF EXIST avaliacaoCategoria;"+"DROP TABLE IF EXIST comentario;" );
        onCreate(db);
    }

    public long insertFaculdade (Faculdade faculdade ){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
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
        initialValues.put(KEY_NOME, categoriaAvaliacaoCadeira.getNome());
        return database.insert(TABLE_CATEGORIAAVALIACAOCADEIRA, null, initialValues);
    }

    public long insertMetodoAvaliacaoCadeira(MetodoAvaliacaoCadeira metodoAvaliacaoCadeira){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOME, metodoAvaliacaoCadeira.getNome());
        return database.insert(TABLE_METODOAVALIACAOCADEIRA, null, initialValues);
    }

    public long insertCadeira(Cadeira cadeira) {
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOME, cadeira.getNome());
        initialValues.put(KEY_NOMEPROFESSOR, cadeira.getNomeProfessor());
        initialValues.put(KEY_NOMECURSO, cadeira.getNomeCurso());
        initialValues.put(KEY_IDFACULDADE, cadeira.getIdFaculdade());
        return database.insert(TABLE_CADEIRA, null, initialValues);
    }

    public long insertAvaliacao(Avaliacao avaliacao){
        database = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_EMAILALUNO, avaliacao.getEmailAluno());
        initialValues.put(KEY_NOMECURSO, avaliacao.getNomeCurso());
        initialValues.put(KEY_IDFACULDADE, avaliacao.getIdFaculdade());
        initialValues.put(KEY_NOMEPROFESSOR, avaliacao.getNomeProfessor());
        initialValues.put(KEY_NOMECADEIRA, avaliacao.getNomeCadeira());
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
        initialValues.put(KEY_IDAVALIACAO, Comentario.getIdAvaliacao());
        initialValues.put(KEY_ANO, Comentario.getAno());
        initialValues.put(KEY_SEMESTRE, Comentario.getSemestre());
        initialValues.put(KEY_TEXT, Comentario.getTexto());
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

    public Cursor getAllCadeira() { //ALTERAR PARA RETORNAR LIST AO INVES DE CURSOR
        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_CADEIRA, new String[] {KEY_NOME,
                KEY_NOMEPROFESSOR, KEY_NOMECURSO, KEY_IDFACULDADE }, null, null, null, null, null);
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

    public void deleteCadeira(Cadeira cadeira){
        database = this.getWritableDatabase();
        database.execSQL("delete from cadeira where nome = '" + cadeira.getNome() + "';");
    }

    public void deleteAvaliacao(Avaliacao avaliacao){
        database = this.getWritableDatabase();
        database.execSQL("delete from avaliacao where id = '" + avaliacao.getId() + "';");
    }

    public void deleteAvaliacaoMetodo(AvaliacaoMetodo avaliacaoMetodo){
        database = this.getWritableDatabase();
        database.execSQL("delete from avaliacaoMetodo where idAvaliacao = '" + avaliacaoMetodo.getIdAvaliacao() + "';");
    }

    public void deleteAvaliacaoCategoria(AvaliacaoCategoria avaliacaoCategoria){
        database = this.getWritableDatabase();
        database.execSQL("delete from avaliacaoCategoria where idAvaliacao = '" + avaliacaoCategoria.getIdAvaliacao() + "';");
    }

    public void deleteComentario(Comentario comentario){
        database = this.getWritableDatabase();
        database.execSQL("delete from comentario where id = '" + comentario.getId() + "';");
    }

    public Aluno getAluno(String email){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from aluno where email = '" + email + "'", null);

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

        Cursor cursor = database.rawQuery("select * from faculdade where id = " + id, null);

        Faculdade retorno = new Faculdade();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));
            retorno.setSigla(cursor.getString(cursor.getColumnIndex(KEY_SIGLA)));

        }

        return retorno;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Curso getCurso(String nome, int idFaculdade){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from curso where idFaculdade = " + idFaculdade + " and nome = '" + nome + "'", null);

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
        Cursor cursor = database.rawQuery("select * from categoriaAvaliacaoCadeira where id = " + id, null);

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
        Cursor cursor = database.rawQuery("select * from metodoAvaliacaoCadeira where id = " + id, null);

        MetodoAvaliacaoCadeira retorno = new MetodoAvaliacaoCadeira();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));

        }

        return retorno;
    }
 ///////////////////////////////////////mesmo pro int

    public Cadeira getCadeira(String nome, String nomeProfessor, String nomeCurso, int idFaculdade){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from cadeira where nome = '" + nome + "' and nomeProfessor = '" + nomeProfessor + "' and nomeCurso = '" + nomeCurso + "' and idFaculdade = " + idFaculdade, null);

        Cadeira retorno = new Cadeira();
        if(cursor.moveToNext()){
            retorno.setNomeProfessor(cursor.getString(cursor.getColumnIndex(KEY_NOMEPROFESSOR)));
            retorno.setNome(cursor.getString(cursor.getColumnIndex(KEY_NOME)));
            retorno.setNomeCurso(cursor.getString(cursor.getColumnIndex(KEY_NOMECURSO)));
            retorno.setIdFaculdade(cursor.getInt(cursor.getColumnIndex(KEY_IDFACULDADE)));
        }

        return retorno;
    }

    public Avaliacao getAvaliacao(int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from avaliacao where id = " + id, null);

        Avaliacao retorno = new Avaliacao();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setEmailAluno(cursor.getString(cursor.getColumnIndex(KEY_EMAILALUNO)));
            retorno.setNomeCurso(cursor.getString(cursor.getColumnIndex(KEY_NOMECURSO)));
            retorno.setIdFaculdade(cursor.getInt(cursor.getColumnIndex(KEY_IDFACULDADE)));
            retorno.setNomeProfessor(cursor.getString(cursor.getColumnIndex(KEY_NOMEPROFESSOR)));
            retorno.setNomeCadeira(cursor.getString(cursor.getColumnIndex(KEY_NOMECADEIRA)));

        }

        return retorno;
    }


    /////////////////////////////////////////mesmo pro int id

    public AvaliacaoMetodo getAvaliacaoMetodo(int idAvaliacao){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from avaliacaoMetodo where idAvaliacao = " + idAvaliacao, null);

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
        Cursor cursor = database.rawQuery("select * from avaliacaoCategoria where idAvaliacao = " + idAvaliacao, null);

        AvaliacaoCategoria retorno = new AvaliacaoCategoria();
        if(cursor.moveToNext()){
            retorno.setIdAvaliacao(cursor.getInt(cursor.getColumnIndex(KEY_IDAVALIACAO)));
            retorno.setIdCategoria(cursor.getInt(cursor.getColumnIndex(KEY_IDCATEGORIA)));
            retorno.setNota(cursor.getInt(cursor.getColumnIndex(KEY_NOTA)));

        }

        return retorno;
    }
///////////////////////////////////////////mesmo pro

    public Comentario getComentario(int id){
        database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from comentario where idAvaliacao = " + id, null);

        Comentario retorno = new Comentario();
        if(cursor.moveToNext()){
            retorno.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            retorno.setIdAvaliacao(cursor.getInt(cursor.getColumnIndex(KEY_IDAVALIACAO)));
            retorno.setAno(cursor.getString(cursor.getColumnIndex(KEY_ANO)));
            retorno.setSemestre(cursor.getString(cursor.getColumnIndex(KEY_SEMESTRE)));
            retorno.setTexto(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
        }

        return retorno;
    }


}
