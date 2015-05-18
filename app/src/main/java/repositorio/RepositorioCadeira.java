package repositorio;

import android.database.Cursor;

import database.Database;
import objeto.Cadeira;

/**
 * Created by Diogo on 17/05/2015.
 */
public class RepositorioCadeira {
    //CRUD ENTRA AQUI
    private Database db;

    public RepositorioCadeira(Database db){
        this.db = db;
    }

    public void insert(Cadeira cadeira){
        db.insertCadeira(cadeira);
    }

    public void delete(Cadeira cadeira){
        db.deleteCadeira(cadeira);
    }

    public Cursor list(){
        return db.getAllAlunos();
    }

    public Cadeira get(String nome, String nomeProfessor, String nomeCurso, int idFaculdade){
        return db.getCadeira(nome, nomeProfessor, nomeCurso, idFaculdade);
    }
}
