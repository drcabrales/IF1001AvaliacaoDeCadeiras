package repositorioParse;

import java.util.List;

import objetoParse.ParseAvaliacao;
import objetoParse.ParseAvaliacaoMetodo;
import database.Database;
import objetoParse.ParseMetodoAvaliacaoCadeira;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioAvaliacaoMetodoParse {



    private Database db;

    public RepositorioAvaliacaoMetodoParse(Database db){
        this.db = db;
    }

    public void insert(ParseAvaliacaoMetodo parseAvaliacaoMetodo){
        db.insertAvaliacaoMetodoObj(parseAvaliacaoMetodo);
    }

    public void delete(ParseAvaliacaoMetodo parseAvaliacaoMetodo){
        db.deleteAvaliacaoMetodoObj(parseAvaliacaoMetodo);
    }
/*
    public List<ParseAvaliacaoMetodo> list(){
        return db.getAllParseAvaliacaoMetodoObj();
    }

*/
    public ParseAvaliacaoMetodo get(ParseAvaliacao parseAvaliacao, ParseMetodoAvaliacaoCadeira parseMetodoAvaliacaoCadeira){
        return db.getParseAvaliacaoMetodoObj(parseAvaliacao, parseMetodoAvaliacaoCadeira);
    }


}
