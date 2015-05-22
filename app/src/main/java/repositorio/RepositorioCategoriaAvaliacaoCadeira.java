package repositorio;

import android.database.Cursor;

import java.util.List;

import database.Database;
import objeto.CategoriaAvaliacaoCadeira;

/**
 * Created by ezequiel on 17/05/2015.
 */
public class RepositorioCategoriaAvaliacaoCadeira {

    private Database db;

    public RepositorioCategoriaAvaliacaoCadeira(Database db){
        this.db = db;
    }

    public void insert(CategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira){
        db.insertCategoriaAvaliacaoCadeira(categoriaAvaliacaoCadeira);
    }

    public void delete(CategoriaAvaliacaoCadeira categoriaAvaliacaoCadeira){
        db.deleteCategoriaAvaliacaoCadeira(categoriaAvaliacaoCadeira);
    }

    public List<CategoriaAvaliacaoCadeira> list(){
        return db.getAllCategoriaAvaliacaoCadeira();
    }


    public CategoriaAvaliacaoCadeira get(int id){
        return db.getCategoriaAvaliacaoCadeira(id);
    }
}
