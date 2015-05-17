package repositorio;

import android.database.Cursor;

import database.Database;
import objeto.AvaliacaoMetodo;

/**
 * Created by ezequiel on 17/05/2015.
 */
public class RepositorioAvaliacaoMetodo {

    private Database db;

    public RepositorioAvaliacaoMetodo(Database db){
        this.db = db;
    }

    public void insert(AvaliacaoMetodo avaliacaoMetodo){
        db.insertAvaliacaoMetodo(avaliacaoMetodo);
    }

    public void delete(AvaliacaoMetodo avaliacaoMetodo){
        db.deleteAvaliacaoMetodo(avaliacaoMetodo);
    }

    public Cursor list(){
        return db.getAllAvaliacaoMetodo();
    }


    public AvaliacaoMetodo get(int idAvaliacao){
        return db.getAvaliacaoMetodo(idAvaliacao);
    }
}
