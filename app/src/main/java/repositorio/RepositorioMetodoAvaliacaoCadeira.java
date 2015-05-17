package repositorio;

import android.database.Cursor;

import database.Database;
import objeto.MetodoAvaliacaoCadeira;

/**
 * Created by ezequiel on 17/05/2015.
 */
public class RepositorioMetodoAvaliacaoCadeira {

    private Database db;

    public RepositorioMetodoAvaliacaoCadeira(Database db){
        this.db = db;
    }

    public void insert(MetodoAvaliacaoCadeira metodoAvaliacaoCadeira){
        db.insertMetodoAvaliacaoCadeira(metodoAvaliacaoCadeira);
    }

    public void delete(MetodoAvaliacaoCadeira metodoAvaliacaoCadeira){
        db.deleteMetodoAvaliacaoCadeira(metodoAvaliacaoCadeira);
    }

    public Cursor list(){
        return db.getAllMetodoAvaliacaoCadeira();
    }


    public MetodoAvaliacaoCadeira get(int id){
        return db.getMetodoAvaliacaoCadeira(id);
    }
}
