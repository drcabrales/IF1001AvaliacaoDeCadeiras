package repositorioParse;

import com.parse.ParseObject;

import java.util.ArrayList;
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
    public ArrayList<ParseObject> getAll(){
        return db.getAll("Aluno");
    }
    public ParseAluno get(String email){
        return db.getParseAlunoObj(email);
    }
    public ParseAluno getById(String id){
        return db.getParseAlunoObjById(id);
    }
    public ParseAluno login(String email, String password) {return db.login(email,password);}


}
