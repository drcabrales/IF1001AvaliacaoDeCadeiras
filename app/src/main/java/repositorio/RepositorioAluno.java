package repositorio;

import android.database.Cursor;

import java.util.List;

import database.Database;
import objeto.Aluno;

/**
 * Created by Diogo on 15/05/2015.
 */
public class RepositorioAluno {
    //CRUD ENTRA AQUI
    private Database db;

    public RepositorioAluno(Database db){
        this.db = db;
    }

    public void insert(Aluno aluno){
        db.insertAluno(aluno);
    }

    public void delete(Aluno aluno){
        db.deleteAluno(aluno);
    }

   public List<Aluno> list(){
        return db.getAllAlunos();
    }

    public Aluno get(String email){
        return db.getAluno(email);
    }

}