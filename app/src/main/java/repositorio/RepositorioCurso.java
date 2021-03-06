package repositorio;

import android.database.Cursor;

import java.util.List;

import database.Database;

import objeto.Curso;

/**
 * Created by ezequiel on 17/05/2015.
 */
public class RepositorioCurso {


    private Database db;

    public RepositorioCurso(Database db){
        this.db = db;
    }

    public void insert(Curso curso){
        db.insertCurso(curso);
    }

    public void delete(Curso curso){
        db.deleteCurso(curso);
    }

    public List<Curso> list(){
        return db.getAllCurso();
    }


    public Curso get(String nome, int idFaculdade){
        return db.getCurso(nome, idFaculdade);
    }
}
