package repositorioParse;

import java.util.List;

import database.Database;
import objetoParse.ParseAluno;

/**
 * Created by ezequiel on 04/06/2015.
 */
public class RepositorioAlunoParse {


    private Database db;

    public RepositorioAlunoParse(Database db){
        this.db = db;
    }

    public void insert(ParseAluno parseAluno){
        db.insertAlunoObj(parseAluno);
    }


    public void delete(ParseAluno parseAluno){
        db.deleteAlunoObj(parseAluno);
    }
/*
    public List<ParseAluno> list(){
        return db.getAllParseAlunoObj();
    }
*/
    public ParseAluno get(String email){
        return db.getParseAlunoObj(email);
    }


}
