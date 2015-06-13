package repositorioParse;

import java.util.List;
import database.Database;
import objetoParse.ParseAluno;
import objetoParse.ParseAvaliacao;
import objetoParse.ParseCadeira;


/**
 * Created by ezequiel on 05/06/2015.
 */
public class RepositorioAvaliacaoParse {


    private Database db;

    public RepositorioAvaliacaoParse(Database db){
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
*/

    public ParseAvaliacao get(ParseAluno parseAluno, ParseCadeira parseCadeira){
        return db.getParseAvaliacaoObj(parseAluno,parseCadeira);
    }


}
