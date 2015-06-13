package repositorioParse;

import java.util.List;

import objetoParse.ParseAvaliacao;
import objetoParse.ParseAvaliacaoCategoria;
import database.Database;
import objetoParse.ParseCategoriaAvaliacaoCadeira;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioAvaliacaoCategoriaParse {

    private Database db;

    public RepositorioAvaliacaoCategoriaParse(Database db){
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
*/

    public ParseAvaliacaoCategoria get(ParseAvaliacao parseAvaliacao, ParseCategoriaAvaliacaoCadeira parseCategoriaAvaliacaoCadeira){
        return db.getParseAvaliacaoCategoriaObj(parseAvaliacao, parseCategoriaAvaliacaoCadeira);
    }


}
