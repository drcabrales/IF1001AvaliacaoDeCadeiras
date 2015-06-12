package repositorio;

import java.util.List;
import database.Database;
import objetoParse.ParseAvaliacao;



/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioAvaliacaoObj {


    private Database db;

    public RepositorioAvaliacaoObj(Database db){
        this.db = db;
    }

    public void insert(ParseAvaliacao parseAvaliacao){
        db.insertAvaliacaoObj(parseAvaliacao);
    }

    public void delete(ParseAvaliacao parseAvaliacao){
        db.deleteAvaliacaoObj(parseAvaliacao);
    }
/*
    public List<ParseAvaliacao> list(){
        return db.getAllParseAvaliacaoObj();
    }


    public ParseAvaliacao get(int id){
        return db.getParseAvaliacaooObj(id);
    }
*/

}
