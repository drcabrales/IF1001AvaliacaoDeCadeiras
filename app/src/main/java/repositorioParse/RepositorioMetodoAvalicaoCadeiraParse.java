package repositorioParse;

import java.util.List;
import objetoParse.ParseMetodoAvaliacaoCadeira;
import database.Database;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioMetodoAvalicaoCadeiraParse {


    private Database db;

    public RepositorioMetodoAvalicaoCadeiraParse(Database db){
        this.db = db;
    }

    public void insert(ParseMetodoAvaliacaoCadeira parseMetodoAvaliacaoCadeir){
        db.insertMetodoAvaliacaoCadeiraObj(parseMetodoAvaliacaoCadeir);
    }

    public void delete(ParseMetodoAvaliacaoCadeira parseMetodoAvaliacaoCadeir){
        db.deleteMetodoAvaliacaoCadeiraObj(parseMetodoAvaliacaoCadeir);
    }
/*
    public List<ParseMetodoAvaliacaoCadeir> list(){
        return db.getAllParseMetodoAvaliacaoCadeirObj();
    }
*/

    public ParseMetodoAvaliacaoCadeira get(String nome){
        return db.getParseMetodoAvaliacaoCadeirObj(nome);
    }


}
