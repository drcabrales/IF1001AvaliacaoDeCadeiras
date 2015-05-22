package repositorio;

import android.database.Cursor;

import java.util.List;

import database.Database;
import objeto.AvaliacaoCategoria;

/**
 * Created by ezequiel on 17/05/2015.
 */
public class RepositorioAvaliacaoCategoria {

    private Database db;

    public RepositorioAvaliacaoCategoria(Database db){
        this.db = db;
    }

    public void insert(AvaliacaoCategoria avaliacaoCategoria){
        db.insertAvaliacaoCategoria(avaliacaoCategoria);
    }

    public void delete(AvaliacaoCategoria avaliacaoCategoria){
        db.deleteAvaliacaoCategoria(avaliacaoCategoria);
    }

    public List<AvaliacaoCategoria> list(){
        return db.getAllAvaliacaoCategoria();
    }


    public AvaliacaoCategoria get(int idAvaliacao){
        return db.getAvaliacaoCategoria(idAvaliacao);
    }
}
