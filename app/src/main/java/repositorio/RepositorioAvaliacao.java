package repositorio;

import android.database.Cursor;

import database.Database;
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

    public Cursor list(){
        return db.getAllAvaliacao();
    }


    public Avaliacao get(int id){
        return db.getAvaliacao(id);
    }
}
