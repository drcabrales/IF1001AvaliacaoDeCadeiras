package repositorio;

import java.util.List;
import objetoParse.ParseMetodoAvaliacaoCadeira;
import database.Database;

/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioMetodoAvalicaoCadeiraObj {


    private Database db;

    public RepositorioMetodoAvalicaoCadeiraObj(Database db){
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


    public ParseMetodoAvaliacaoCadeir get(int id){
        return db.getParseMetodoAvaliacaoCadeirObj(id);
    }
*/

}
