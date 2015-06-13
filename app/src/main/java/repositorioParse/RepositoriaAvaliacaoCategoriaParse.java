package repositorioParse;

import java.util.List;
import objetoParse.ParseAvaliacaoCategoria;
import database.Database;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositoriaAvaliacaoCategoriaParse {

    private Database db;

    public RepositoriaAvaliacaoCategoriaParse(Database db){
        this.db = db;
    }

    public void insert(ParseAvaliacaoCategoria parseAvaliacaoCategoria){
        db.insertAvaliacaoCategoriaObj(parseAvaliacaoCategoria);
    }

    public void delete(ParseAvaliacaoCategoria parseAvaliacaoCategoria){
        db.deleteAvaliacaoCategoriaObj(parseAvaliacaoCategoria);
    }
/*
    public List<ParseAvaliacaoCategoria> list(){
        return db.getAllParseAvaliacaoCategoriaObj();
    }


    public ParseAvaliacaoCategoria get(int idAvaliacao){
        return db.getParseAvaliacaoCategoriaObj(idAvaliacao);
    }

*/
}
