package repositorio;

import android.database.Cursor;

import java.util.List;

import database.Database;
import objeto.Aluno;
import objeto.Avaliacao;

/**
 * Created by ezequiel on 17/05/2015.
 */
public class RepositorioAvaliacao {

    private Database db;

    public RepositorioAvaliacao(Database db){
        this.db = db;
    }

    public void insert(Avaliacao avaliacao){
        db.insertAvaliacao(avaliacao);
    }

    public void delete(Avaliacao avaliacao){
        db.deleteAvaliacao(avaliacao);
    }

    public List<Avaliacao> list(){
        return db.getAllAvaliacao();
    }


    public Avaliacao get(int id){
        return db.getAvaliacao(id);
    }
}
